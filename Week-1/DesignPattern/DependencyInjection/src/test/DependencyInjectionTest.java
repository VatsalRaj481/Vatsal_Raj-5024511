package test;

//CustomerRepository.java
interface CustomerRepository {
 void addCustomer(String customerName);
}

//CustomerRepositoryImpl.java (Concrete Implementation)
class CustomerRepositoryImpl implements CustomerRepository {
 @Override
 public void addCustomer(String customerName) {
     System.out.println("Customer added: " + customerName);
 }
}

//CustomerService.java (Service Class)
class CustomerService {
 private CustomerRepository repository;

 // Constructor Injection
 public CustomerService(CustomerRepository repository) {
     this.repository = repository;
 }

 public void addCustomer(String customerName) {
     repository.addCustomer(customerName);
 }
}

//DependencyInjectionTest.java
public class DependencyInjectionTest {
 public static void main(String[] args) {
     CustomerRepository repository = new CustomerRepositoryImpl();
     CustomerService service = new CustomerService(repository);

     service.addCustomer("Alice");
 }
}
