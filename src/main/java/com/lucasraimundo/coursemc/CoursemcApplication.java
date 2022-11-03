package com.lucasraimundo.coursemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {
	
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

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlandia", s1);
		City c2 = new City(null, "São Paulo", s2);
		City c3 = new City(null, "Campinas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "488.456.448.77", TypeClient.PESSOAFISICA);
		
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
