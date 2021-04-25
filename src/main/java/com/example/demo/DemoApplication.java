package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.enums.ClienteType;
import com.example.demo.enums.StatePayment;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication implements CommandLineRunner  {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * CommandLineRunner
	 * executa o código que estiver dentro de run
	 * aqui estamos instanciando os objetos do modelo concentual
	 * */
	@Override
	public void run(String... args) throws ParseException {
		Category c1 = new Category(null, "Informática");
		Category c2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategorys().addAll(Arrays.asList(c1));
		p2.getCategorys().addAll(Arrays.asList(c1, c2));
		p3.getCategorys().addAll(Arrays.asList(c1));

		categoryRepository.saveAll(Arrays.asList(c1, c2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st = new State(null, "Minas Gerais");
		State st1 = new State(null, "São Paulo");

		City city = new City(null, "Uberlândia", st);
		City city2 = new City(null, "São Paulo", st1);
		City city3 = new City(null, "Campinas", st1);

		st.getCitys().addAll(Arrays.asList(city));
		st1.getCitys().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(st, st1));
		cityRepository.saveAll(Arrays.asList(city, city2, city3));

		Client cli = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClienteType.PERSON);
		cli.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address adress = new Address(null, "Rua Flores", "300","Apto 303", "Jardim", "38220834", cli, city);
		Address adress2 = new Address(null, "Avenida Matos", "105","Sala 800", "Centro", "38777012", cli, city2);

		cli.getAddress().addAll(Arrays.asList(adress, adress2));

		clientRepository.saveAll(Arrays.asList(cli));
		addressRepository.saveAll(Arrays.asList(adress, adress2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli, adress);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli, adress2);

		Payment payment1 = new PaymentWithCard(null, StatePayment.SETTLED, ord1, 6);
		ord1.setPayment(payment1);

		Payment payment2 = new PaymentWithTicket(null, StatePayment.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(payment2);

		cli.getOrders().addAll(Arrays.asList(ord1, ord2));

		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));

		ItemOrder ip1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
		ItemOrder ip3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);

		ord1.getItens().addAll(Arrays.asList(ip1, ip2));
		ord2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemOrderRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
