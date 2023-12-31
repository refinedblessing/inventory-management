# Inventory Management Application

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## Overview

This Inventory Management Application is an API designed to help businesses manage their inventory, purchase orders, suppliers etc. efficiently.

## Features

- **Category Management**: Create, update, and delete product categories.

- **Item Management**: Manage product items, including name, short & long description, price, and quantity.

- **Supplier Management**: Keep track of suppliers, including their contact details.

- **Store Management**: Manage stores with location, type, and opening date.

- **Inventory Control**: Keep inventory information for stores, manage quantity, and set thresholds, find inventories at threshold.

- **Purchase Orders**: Create, approve, and track purchase orders with various statuses, update store inventory when purchase order is delivered.

- **User Management**: Admin can create user, and assign roles and stores to user.

- **Exception Handling**: Most Exceptions have been handled using a CustomizedErrorHandler.

- **Testing**: WIP


## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)

## Getting Started

### Frontend Demo
<div>
    <a href="https://www.loom.com/share/ad8dea3f2aed4a94aa1309d26b03f108">
      <p>Store Management Application Demo - Watch Video</p>
    </a>
    <a href="https://www.loom.com/share/ad8dea3f2aed4a94aa1309d26b03f108">
      <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/ad8dea3f2aed4a94aa1309d26b03f108-with-play.gif">
    </a>
  </div>

### Code and Technology Overview
<div>
    <a href="https://www.loom.com/share/d2b77da5cb7b477f9f34d826289ca80a">
      <p>Building a Store Management Application: Code and Technology Overview - Watch Video</p>
    </a>
    <a href="https://www.loom.com/share/d2b77da5cb7b477f9f34d826289ca80a">
      <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/d2b77da5cb7b477f9f34d826289ca80a-1700094122903-with-play.gif">
    </a>
  </div>

### Hosting

- **Frontend**: A User interface built using Next.js React Framework, Typescript and TailwindCSS, the [repository](https://github.com/refinedblessing/inventory-management-frontend), hosted on Vercel [here](https://inventory-management-frontend-liart.vercel.app).
- **Backend**: API is currently hosted on Azure Web Apps [here](https://inventory-master.azurewebsites.net) with a Swagger UI [here](https://inventory-master.azurewebsites.net/swagger-ui/index.html#/)
- **Slide Show with Demo**: [Slide Show](https://docs.google.com/presentation/d/1Vc1hM2kyANVdmAZTuNA76hEWBr4ssqfux1Mdr82QCc0/edit?usp=sharing)
- If you are not seeing any data from the backend, please be patient, the free tier on Azure is very very slow on the first request, you should see a loading icon on the Frontend while the request is being processed.

### Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed.
- [Apache Maven](https://maven.apache.org/download.cgi) for building and packaging the application.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/refinedblessing/inventory-management.git

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
I use Swagger UI https://inventory-master.azurewebsites.net/swagger-ui/index.html#/
