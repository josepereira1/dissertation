# bin/sh

HOST=localhost
TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGFkbWluIiwicm9sZSI6IkFETUlOIiwiaXNzIjoiNkZNQUpCMG5QdVVVRjNCTnI5cm1vTmR5UDJqQ3YwM1MiLCJleHAiOjk5OTk5OTk5OTk5OSwiaWF0IjoxNjQ2NjEwNDgxLCJlbWFpbCI6ImV4ZW1wbG9AZW1haWwuY29tIn0.YnZua_GjH8mE_nF2ts_52l6cHX24_GA3vt2h06aA2dg

# create versions

curl --location --request POST $HOST':8000/api/versions/cc' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "groupId": "tree",
    "description": "tree",
    "value": 0
}'

echo ""

curl --location --request POST $HOST':8000/api/versions/qct' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "groupId": "tree",
    "description": "tree",
    "value": 0
}'

echo ""

# create root category

curl --location --request POST $HOST':8000/api/categories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "#root"
}'

echo ""

# create default consumer

curl --location --request POST $HOST':8000/api/consumers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "bob",
    "password": "password",
    "contacts": {
        "email": "exemplo@email.com", 
        "mobileNumber": "123456789"
    },
    "deliveryAddress": {
        "address": "Rua da Spring Boot",
        "city": "Lisbon",
        "postalCode": "334343",
        "country": "Portugal"
    }
}'

echo ""

# create default manager

curl --location --request POST $HOST':8000/api/managers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "alice",
    "password": "password",
    "email": "exemplo@email.com"
}'

echo ""

# setup saga definitions

curl --location --request POST $HOST':8000/api/sagasdefinitions' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "create.product.saga.0",
    "exchange": "saga.publisher.exchange",
    "successfullyMessage": "Product created successfully.",
    "successfullyCode": 200,
    "commitRoutingKey": "create.product.saga.0.commit",
    "commitMethodName": "create.product.saga.0.commit",
    "httpMethod": "POST",
    "roles": ["MANAGER", "ADMIN"],
    "jsonSchema": {
  "title": "message",
  "type": "object",
  "properties": {
    "id": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "shortDetails": {
    },
    "longDetails": {
    },
    "currency": {
      "type": "string"
    },
    "initialPrice": {
      "type": "number"
    },
    "discountPercentage": {
      "type": "number"
    },
    "vatPercentage": {
      "type": "number"
    },
    "shipping": {
      "type": "number"
    },
    "links": {
    },
    "visibility": {
      "type": "string"
    },
    "sku": {
      "type": "string"
    },
    "ean": {
      "type": "string"
    },
    "pn": {
      "type": "string"
    }
  },
  "required": ["id", "name", "shortDetails", "longDetails", "currency", "initialPrice", "discountPercentage", "vatPercentage", "shipping", "links", "visibility", "sku", "ean", "pn"],
  "additionalProperties": false
},
    "outputParams": ["id"],
    "participants": {
        "0": {
            "serviceName": "cp",
            "routingKey": "create.product.saga.0.cp.create.product",
            "localTransaction": "create.product.saga.0.cp.local.transaction",
            "compensatingTransaction": "create.product.saga.0.cp.compensating.transaction"
        },
        "1": {
            "serviceName": "cc",
            "routingKey": "create.product.saga.0.cc.create.product",
            "localTransaction": "create.product.saga.0.cc.local.transaction",
            "compensatingTransaction": "create.product.saga.0.cc.compensating.transaction"
        },
        "2": {
            "serviceName": "inventory",
            "routingKey": "create.product.saga.0.inventory.create.product",
            "localTransaction": "create.product.saga.0.inventory.local.transaction",
            "compensatingTransaction": "create.product.saga.0.inventory.compensating.transaction"
        },
        "3": {
            "serviceName": "sc",
            "routingKey": "create.product.saga.0.sc.create.product",
            "localTransaction": "create.product.saga.0.sc.local.transaction",
            "compensatingTransaction": "create.product.saga.0.sc.compensating.transaction"
        }
    }
}'

echo ""

curl --location --request POST $HOST':8000/api/sagasdefinitions' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "update.product.saga.0",
    "exchange": "saga.publisher.exchange",
    "successfullyMessage": "Product updated successfully.",
    "successfullyCode": 200,
    "commitRoutingKey": "update.product.saga.0.commit",
    "commitMethodName": "update.product.saga.0.commit",
    "httpMethod": "PUT",
    "roles": ["MANAGER", "ADMIN"],
    "jsonSchema": {
  "title": "message",
  "type": "object",
  "properties": {
    "id": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "shortDetails": {
    },
    "longDetails": {
    },
    "currency": {
      "type": "string"
    },
    "initialPrice": {
      "type": "number"
    },
    "discountPercentage": {
      "type": "number"
    },
    "vatPercentage": {
      "type": "number"
    },
    "shipping": {
      "type": "number"
    },
    "links": {
    },
    "visibility": {
      "type": "string"
    },
    "sku": {
      "type": "string"
    },
    "ean": {
      "type": "string"
    },
    "pn": {
      "type": "string"
    }
  },
  "required": ["id"],
  "additionalProperties": false
},
    "outputParams": ["id"],
    "participants": {
        "0": {
            "serviceName": "cp",
            "routingKey": "update.product.saga.0.cp.update.product",
            "localTransaction": "update.product.saga.0.cp.local.transaction",
            "compensatingTransaction": "update.product.saga.0.cp.compensating.transaction"
        },
        "1": {
            "serviceName": "cc",
            "routingKey": "update.product.saga.0.cc.update.product",
            "localTransaction": "update.product.saga.0.cc.local.transaction",
            "compensatingTransaction": "update.product.saga.0.cc.compensating.transaction"
        },
        "2": {
            "serviceName": "inventory",
            "routingKey": "update.product.saga.0.inventory.update.product",
            "localTransaction": "update.product.saga.0.inventory.local.transaction",
            "compensatingTransaction": "update.product.saga.0.inventory.compensating.transaction"
        },
        "3": {
            "serviceName": "sc",
            "routingKey": "update.product.saga.0.sc.update.product",
            "localTransaction": "update.product.saga.0.sc.local.transaction",
            "compensatingTransaction": "update.product.saga.0.sc.compensating.transaction"
        }
    }
}'

echo ""

curl --location --request POST $HOST':8000/api/sagasdefinitions' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "create.order.saga.0",
    "exchange": "saga.publisher.exchange",
    "successfullyMessage": "Order created successfully.",
    "successfullyCode": 200,
    "commitRoutingKey": "create.order.saga.0.commit",
    "commitMethodName": "create.order.saga.0.commit",
    "httpMethod": "POST",
    "roles": ["CONSUMER"],
    "jsonSchema": {
  "title": "message",
  "type": "object",
  "properties": {
    "tin": {
      "type": "string"
    },
    "chargeAddress": {
      "type": "object",
      "properties": {
        "address": {
          "type": "string"
        },
        "city":{
          "type": "string"
        },
        "postalCode": {
          "type": "string"
        },
        "country": {
          "type": "string"
        }
      },
      "required": ["address", "city", "postalCode", "country"]
    },
    "deliveryAddress": {
      "type": "object",
      "properties": {
        "address": {
          "type": "string"
        },
        "city":{
          "type": "string"
        },
        "postalCode": {
          "type": "string"
        },
        "country": {
          "type": "string"
        }
      },
      "required": ["address", "city", "postalCode", "country"],
      "additionalProperties": false
    },
    "other": {

    }
  },
  "required": ["chargeAddress", "deliveryAddress"],
  "additionalProperties": false
},
    "outputParams": ["id"],
    "participants": {
        "0": {
            "serviceName": "sc",
            "routingKey": "create.order.saga.0.sc.create.order",
            "localTransaction": "create.order.saga.0.sc.local.transaction",
            "compensatingTransaction": "create.order.saga.0.sc.compensating.transaction"
        },
        "1": {
            "serviceName": "qp",
            "routingKey": "create.order.saga.0.qp.create.order",
            "localTransaction": "create.order.saga.0.qp.local.transaction",
            "compensatingTransaction": "create.order.saga.0.qp.compensating.transaction"
        },
        "2": {
            "serviceName": "co",
            "routingKey": "create.order.saga.0.co.create.order",
            "localTransaction": "create.order.saga.0.co.local.transaction",
            "compensatingTransaction": "create.order.saga.0.co.compensating.transaction"
        },
        "3": {
            "serviceName": "inventory",
            "routingKey": "create.order.saga.0.inventory.create.order",
            "localTransaction": "create.order.saga.0.inventory.local.transaction",
            "compensatingTransaction": "create.order.saga.0.inventory.compensating.transaction"
        }
    }
}'

echo ""