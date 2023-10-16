# Inventory Management Application

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## Overview

The Inventory Management Application is a web-based system designed to help businesses manage their inventory, purchase orders, and suppliers efficiently. This application allows you to manage categories, items, stores, and purchase orders with ease.

## Features

- **Category Management**: Create, update, and delete product categories.

- **Item Management**: Manage product items, including name, description, price, and quantity.

- **Supplier Management**: Keep track of suppliers, including their contact details.

- **Store Management**: Manage stores with location, type, and opening date.

- **Inventory Control**: Keep inventory information for stores, manage quantity, and set thresholds.

- **Purchase Orders**: Create, approve, and track purchase orders with various statuses.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed.
- [Apache Maven](https://maven.apache.org/download.cgi) for building and packaging the application.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/inventory-management.git

2. Navigate to the project directory:
    ```bash
    cd inventory-management
   
3. Build the project using Maven:
    ```bash
    mvn clean package
   
4. Run the application:
    ```bash
   java -jar target/inventory-management.jar

The application will be accessible at http://localhost:8080

### Usage
Access the application in your web browser by navigating to http://localhost:8080.
Use the web interface to create, update, or delete categories, items, stores, and purchase orders.
Manage your inventory, view purchase orders, and track your suppliers.

### API Documentation
We use swagger ui http://localhost:8080/swagger-ui/index.html#/
