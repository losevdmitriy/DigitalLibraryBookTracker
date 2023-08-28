# Digital Library Book Tracker

## Description

The Digital Library Book Tracker is a web application designed to facilitate the transition of book management in local libraries to a digital platform. This project utilizes Hibernate and Spring Data JPA, providing librarians with a comprehensive tool to manage readers, track book borrowing, and streamline administrative tasks. The application also includes advanced features such as pagination, sorting, searching, and overdue book checks.

## Features

- User-friendly web interface for librarians to manage readers and books.
- Comprehensive book tracking system, including issuance and return functionalities.
- Support for pagination, sorting, and search to efficiently manage a growing book collection.
- Automatic verification for overdue books to ensure timely returns.
- Field validation using Spring Validator to maintain data integrity.
- Intuitive interfaces for assigning and releasing books to/from readers.
- Quick access to individual reader and book details.

## Entities

### Person Entity

- Stores reader information.
- Fields: 
  - Full Name (unique)
  - Birth Year.

### Book Entity

- Stores book information.
- Fields: 
  - Title
  - Author
  - Year.
   
Relationship: One-to-Many
- Each person can have multiple books.
- Each book belongs to only one person.

## Database

- Utilizes a relational database (e.g., PostgreSQL, MySQL).
- Tables: "Person" and "Book".
- Automatic generation of unique identifiers (id).

## Functionality

- Add, edit, and delete person details.
- Add, edit, and delete book information.
- List of all people and books with clickable links to individual pages.
- Individual pages for people and books displaying relevant information.
- Book assignment and release functionalities for readers.
- Pagination and sorting options for book list.
- Book search functionality based on initial letters of the title.
- Automated checks for overdue books.

## Usage

1. Run the application.
2. Access the application through a web browser: http://localhost:8080.
