package com.lucasraimundo.coursemc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lucasraimundo.coursemc.domain.Adress;
import com.lucasraimundo.coursemc.domain.City;
import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.domain.enums.Profile;
import com.lucasraimundo.coursemc.domain.enums.TypeClient;
import com.lucasraimundo.coursemc.dto.ClientDTO;
import com.lucasraimundo.coursemc.dto.ClientNewDTO;
import com.lucasraimundo.coursemc.repositories.AdressRepository;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.security.UserSS;
import com.lucasraimundo.coursemc.services.exceptions.AuthorizationException;
import com.lucasraimundo.coursemc.services.exceptions.DataIntegrityException;
import com.lucasraimundo.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	public Client find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionadas");
		}
	}
	
	public List<Client> findAll(){
		return repo.findAll();
		}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpf(), TypeClient.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City cid = new City(objDto.getCityId(), null, null);
		Adress end = new Adress(null, objDto.getLogadouro(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getCep(), cli, cid);
		cli.getAdresses().add(end);
		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2()!=null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3()!=null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}
	
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		adressRepository.saveAll(obj.getAdresses());
		return obj;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
		
	}
}
