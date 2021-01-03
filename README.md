# product_comparison_service

This application compares products based on names and categories from different sources.

<br/>

## Commands to run the application on Docker:

##### 1. To build a Docker image using docker-compose.yml:

`docker-compose up --build`

##### 2. To stop the running containers: 

`docker-compose down`

# Scope of given task:

### 1. API Swagger Documentation:

![diagram](docs_screenshots/api_swagger_doc.png)

### 2. Service endpoint that fetches products based on given name and category:  

![diagram](docs_screenshots/products_based_on_name_category.png)


## Data Import scenarios:

### 1. Service endpoint to add a single product:

![diagram](docs_screenshots/add_a_single_product.png)

<br/>

### 2. Service endpoint to upload products in bulk:

###### This endpoint accepts a file as input to upload products in bulk.

![diagram](docs_screenshots/bulk_upload_products_1.png)

![diagram](docs_screenshots/bulk_upload_products_2.png)

<br/>

### 3. DataSourceProductConsumer to consume products from multiple data sources:

![diagram](docs_screenshots/product_comparison_service.png)

<br/>

#### 3.1 KafkaProducer to simulate message-broker as data source scenario:

![diagram](docs_screenshots/produce_message_to_kafka.png)


#### 3.2 Service endpoint to get the consumed product:

![diagram](docs_screenshots/get_single_product.png)
