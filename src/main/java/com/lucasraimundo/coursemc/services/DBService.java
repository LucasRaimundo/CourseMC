package com.lucasraimundo.coursemc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.Adress;
import com.lucasraimundo.coursemc.domain.Category;
import com.lucasraimundo.coursemc.domain.City;
import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.domain.ItemOrder;
import com.lucasraimundo.coursemc.domain.Orders;
import com.lucasraimundo.coursemc.domain.Payment;
import com.lucasraimundo.coursemc.domain.PaymentWithCard;
import com.lucasraimundo.coursemc.domain.PaymentWithTicket;
import com.lucasraimundo.coursemc.domain.Product;
import com.lucasraimundo.coursemc.domain.State;
import com.lucasraimundo.coursemc.domain.enums.StatusPayment;
import com.lucasraimundo.coursemc.domain.enums.TypeClient;
import com.lucasraimundo.coursemc.repositories.AdressRepository;
import com.lucasraimundo.coursemc.repositories.CategoryRepository;
import com.lucasraimundo.coursemc.repositories.CityRepository;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.repositories.ItemOrderRepository;
import com.lucasraimundo.coursemc.repositories.OrdersRepository;
import com.lucasraimundo.coursemc.repositories.PaymentRepository;
import com.lucasraimundo.coursemc.repositories.ProductRepository;
import com.lucasraimundo.coursemc.repositories.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public void instatiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlandia", s1);
		City c2 = new City(null, "São Paulo", s2);
		City c3 = new City(null, "Campinas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Client cli1 = new Client(null, "Maria Silva", "lucasraimundo2010@gmail.com", "488.456.448.77", TypeClient.PESSOAFISICA);
		
		cli1.getPhones().addAll(Arrays.asList("5988-4411", "98465-1078"));
		
		Adress e1 = new Adress(null, "Rua Flores", "300", "Apto 303", "Jardins", "15989356", cli1, c1);
		Adress e2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "45978652", cli1, c2);
		
		cli1.getAdresses().addAll(Arrays.asList(e1, e2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		adressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Orders o1 = new Orders(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Orders o2 = new Orders(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Payment pag1 = new PaymentWithCard(null, StatusPayment.QUITADO, o1, 6);
		o1.setPayment(pag1);
		
		Payment pag2 = new PaymentWithTicket(null, StatusPayment.PENDENTE, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pag2);
		
		cli1.getOrders().addAll(Arrays.asList(o1,o2));
		
		ordersRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemOrder ip1 = new ItemOrder(o1, p1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(o1, p3, 0.00, 2, 80.00);
		ItemOrder ip3 = new ItemOrder(o2, p2, 100.00, 1, 800.00);
		
		o1.getItems().addAll(Arrays.asList(ip1, ip2));
		o2.getItems().addAll(Arrays.asList(ip3));
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		itemOrderRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
