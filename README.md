# E-Learning Platform

A comprehensive Spring Boot-based E-Learning Platform that supports multiple user roles (Students, Instructors, and Administrators) with course managementa and payment processing.

## 🚀 Features

### Core Functionality
- **Multi-Role Authentication** - Support for Students, Instructors, and Administrators
- **Course Management** - Create, update, and manage courses with lessons
- **Payment Processing** - Stripe integration for secure course purchases
- **Email Notifications** - Automated enrollment confirmations
- **Progress Tracking** - Monitor student learning progress
- **Social Authentication** - OAuth2 login with Google and GitHub

### User Roles
- **Students**: Browse courses, enroll in courses, track progress, access learning materials
- **Instructors**: Create and manage courses, upload content, monitor enrollments
- **Administrators**: Manage users, oversee platform operations

## 🛠️ Technology Stack

### Backend
- **Java 21** (Latest LTS)
- **Spring Boot 3.4.0**
- **Spring Security** (Authentication & Authorization)
- **Spring Data JPA** (Database Operations)
- **Spring Mail** (Email Services)
- **Thymeleaf** (Template Engine)

### Database & Storage
- **MySQL** (Primary Database)
- **Hibernate** (ORM)

### External Integrations
- **Stripe** (Payment Processing)
- **OAuth2** (Google & GitHub Authentication)
- **Gmail SMTP** (Email Service)

### Development Tools
- **Lombok** (Code Generation)
- **Maven** (Build Management)
- **Spring Boot DevTools** (Development Utilities)

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **AWS Account** (for S3 storage)
- **Stripe Account** (for payment processing)
- **Gmail Account** (for email services)

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd elearning_thymeleaf
```

### 2. Database Setup
Create a MySQL database named `elearning`:
```sql
CREATE DATABASE elearning;
```

### 3. Configure Application Properties
Update `src/main/resources/application.properties` with your credentials:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/elearning
spring.datasource.username=your_username
spring.datasource.password=your_password

# Email Configuration
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password

# OAuth2 Configuration
spring.security.oauth2.client.registration.github.client-id=your_github_client_id
spring.security.oauth2.client.registration.github.client-secret=your_github_client_secret

spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret

# Stripe Configuration
stripe.api.secret=your_stripe_secret_key
stripe.publishable-key=your_stripe_publishable_key

```

### 4. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📁 Project Structure

```
src/main/java/com/SpringBootProject/E_Learning/
├── config/                 # Configuration classes
│   ├── CustomSuccessHandler.java
│   └── SecurityConfig.java
├── controller/             # REST Controllers
│   ├── AdminController.java
│   ├── InstructorController.java
│   ├── MainController.java
│   ├── RootController.java
│   └── StudentController.java
├── model/                  # JPA Entities
│   ├── Course.java
│   ├── Enrollment.java
│   ├── Lesson.java
│   ├── Provider.java
│   ├── Role.java
│   ├── User.java
├── repository/             # Data Access Layer
│   ├── CourseRepository.java
│   ├── EnrollmentRepository.java
│   ├── LessonRepository.java
│   ├── UserRepository.java
├── service/                # Business Logic
│   ├── CourseService.java
│   ├── EmailService.java
│   ├── EnrollmentService.java
│   ├── PaymentService.java
│   ├── SecurityCustomUserDetailService.java
│   ├── UserService.java
│   └── serviceImpl/        # Service Implementations
└── helpers/                # Utility Classes
    └── Helper.java
```

## 🎯 API Endpoints

### Authentication
- `GET /` - Redirect to home
- `GET /home` - Home page
- `GET /login` - Login page
- `GET /register` - Registration page
- `POST /register` - User registration

### Student Endpoints
- `GET /student/allCourses` - View all available courses
- `POST /student/courses/viewDetails` - View course details
- `GET /student/myCourses` - View enrolled courses
- `POST /student/courses/buy` - Purchase course
- `GET /student/payment/success` - Payment success handler
- `GET /student/payment/cancel` - Payment cancellation
- `GET /student/courses/{id}/lessons` - Access course lessons

### Instructor Endpoints
- `GET /instructor/courses` - View instructor's courses
- `GET /instructor/courses/new` - Create new course form
- `POST /instructor/courses/save` - Save new course
- `GET /instructor/courses/{id}/edit` - Edit course form
- `POST /instructor/courses/{id}/update` - Update course
- `POST /instructor/courses/{id}/delete` - Delete course

### Admin Endpoints
- `GET /admin/instructors` - View all instructors
- `GET /admin/instructors/new` - Add new instructor form
- `POST /admin/instructors/save` - Save new instructor

## 🗄️ Database Schema

### Entities
- **User**: User information with roles and authentication
- **Course**: Course details with pricing and instructor relationship
- **Lesson**: Individual lessons within courses
- **Video**: Video content with S3 storage references
- **Enrollment**: Student-course relationships with progress tracking

### Relationships
- User → Course (One-to-Many) - Instructor creates courses
- User → Enrollment (One-to-Many) - Student enrollments
- Course → Lesson (One-to-Many) - Course contains lessons
- Course → Enrollment (One-to-Many) - Course enrollments

## 🔐 Security Features

- **Role-based Access Control** (RBAC)
- **OAuth2 Social Authentication** (Google, GitHub)
- **Password Encryption** (BCrypt)
- **CSRF Protection**
- **Session Management**
- **Secure Payment Processing** (Stripe)

## 📧 Email Integration

The platform sends automated emails for:
- Course enrollment confirmations
- Payment receipts
- Account notifications

## 💳 Payment Integration

- **Stripe Payment Gateway** integration
- Secure payment processing
- Success/cancellation handling
- Automatic enrollment upon successful payment

## 🚀 Deployment

### Production Deployment
1. Configure production database
2. Set up AWS S3 bucket
3. Configure Stripe production keys
4. Set up production email service
5. Deploy to cloud platform (AWS, Azure, GCP)

### Environment Variables
Set the following environment variables for production:
```bash
SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/elearning
SPRING_DATASOURCE_USERNAME=your-db-username
SPRING_DATASOURCE_PASSWORD=your-db-password
SPRING_MAIL_USERNAME=your-email
SPRING_MAIL_PASSWORD=your-email-password
STRIPE_API_SECRET=your-stripe-secret-key
STRIPE_PUBLISHABLE_KEY=your-stripe-publishable-key
```



---

