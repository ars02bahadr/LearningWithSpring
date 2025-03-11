# LearningWithSpringBoot

# Spring Boot Clean Architecture Template


Bu proje, modern bir Spring Boot uygulaması için hazırlanmış, Clean Architecture prensiplerine uygun olarak tasarlanmış kapsamlı bir şablon projedir. Clean Architecture'ın temel prensipleri olan bağımlılık kuralları ve katmanlar arası soyutlama göz önünde bulundurularak yapılandırılmıştır.

## 🏗️ Clean Architecture Yapısı

Proje, Clean Architecture prensiplerine uygun olarak aşağıdaki katmanlı mimariye sahiptir:

```
src/main/java/com/mycompany/learning/
├── Domain/            # İş kuralları ve entities
│   ├── Entities      # Domain modelleri
│   ├── Repositories  # Repository interfaceleri
│   ├── Results      # Dönüş tipleri için özel sınıflar
│   └── Utilities    # Yardımcı sınıflar
│
├── Application/       # Use case'ler ve uygulama mantığı
│   ├── Dtos         # Data Transfer Objects
│   ├── Mappers      # Entity-DTO dönüşüm sınıfları
│   └── Services     # İş mantığı servisleri
│
├── Infrastructure/    # Dış dünya ile iletişim
│   └──
│
└── Presentation/     # API ve UI katmanı
    └── Controllers  # REST endpoints
```

### 📚 Clean Architecture Katmanları ve Sorumlulukları

1. **Domain Layer (En İç Katman)**
   - İş kurallarını ve entities'leri içerir
   - Hiçbir dış katmana bağımlılığı yoktur
   - Framework'ten bağımsızdır

2. **Application Layer**
   - Use-case'leri içerir
   - Domain katmanını kullanır
   - DTO ve Mapper'lar burada yer alır

3. **Infrastructure Layer**
   - Veritabanı, dosya sistemi, harici servisler gibi dış dünya ile iletişimi sağlar
   - Repository implementasyonları burada yer alır

4. **Presentation Layer**
   - Kullanıcı arayüzü ve API endpoint'leri
   - Controller'lar burada yer alır

## 🛠️ Teknik Detaylar

### Result Pattern
```java
public class Result<T> {
    private boolean success;
    private String message;
    private T data;
    // ...
}
```
- Tutarlı API yanıtları
- Hata yönetimi kolaylığı
- Generic yapı ile tip güvenliği

### Repository Pattern
```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
}
```
- Veritabanı işlemlerinin soyutlanması
- Test edilebilirlik
- Bağımlılıkların yönetimi

## 🔍 Örnek Kullanım Senaryoları

### 1. Yeni Kullanıcı Kaydı
```java
// Controller
@PostMapping("/register")
public Result<UserDto> register(@RequestBody CreateUserDto dto) {
    return userService.register(dto);
}

// Service
@Service
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public Result<UserDto> register(CreateUserDto dto) {
        User user = mapper.toEntity(dto);
        User savedUser = repository.save(user);
        return new SuccessDataResult<>(mapper.toDto(savedUser), "Kayıt başarılı");
    }
}
```

### 2. Veri Listeleme
```java
// Controller
@GetMapping("/users")
public Result<List<UserDto>> getAllUsers() {
    return userService.getAllUsers();
}

// Service
public Result<List<UserDto>> getAllUsers() {
    List<User> users = repository.findAll();
    List<UserDto> dtos = users.stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    return new SuccessDataResult<>(dtos, "Kullanıcılar listelendi");
}
```

## 🚀 Projeyi Geliştirme

1. **Yeni Entity Ekleme**
   ```java
   // 1. Domain katmanında entity oluştur
   // 2. Repository interface'ini tanımla
   // 3. DTO sınıflarını oluştur
   // 4. Mapper interface'ini tanımla
   // 5. Service katmanını implement et
   // 6. Controller'ı oluştur
   ```

2. **Yeni Özellik Ekleme**
   - Domain katmanında gerekli değişiklikleri yap
   - Use-case'leri Application katmanında tanımla
   - Infrastructure katmanında gereken implementasyonları yap
   - API endpoint'lerini Presentation katmanında oluştur

## 📋 Best Practices

1. **SOLID Prensipleri**
   - Single Responsibility Principle
   - Open/Closed Principle
   - Liskov Substitution Principle
   - Interface Segregation Principle
   - Dependency Inversion Principle

2. **Clean Code**
   - Anlamlı isimlendirme
   - Küçük ve odaklı fonksiyonlar
   - DRY (Don't Repeat Yourself)
   - KISS (Keep It Simple, Stupid)

3. **Exception Handling**
   - Global exception handler kullanımı
   - Özel exception sınıfları
   - Anlamlı hata mesajları

## 🔧 Konfigürasyon

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Security
jwt.secret=your_jwt_secret
jwt.expiration=86400000
```


## 📚 Faydalı Kaynaklar

- [Clean Architecture - Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Spring Boot Dokümantasyonu](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [MapStruct Dokümantasyonu](https://mapstruct.org/documentation/stable/reference/html/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

