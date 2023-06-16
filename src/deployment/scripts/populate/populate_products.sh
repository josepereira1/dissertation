# bin/sh

HOST=localhost
SIZE_OF_PRODUCTS_TO_CREATE=450
PRODUCT_START_ID=0
TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGFkbWluIiwicm9sZSI6IkFETUlOIiwiaXNzIjoiNkZNQUpCMG5QdVVVRjNCTnI5cm1vTmR5UDJqQ3YwM1MiLCJleHAiOjk5OTk5OTk5OTk5OSwiaWF0IjoxNjQ2NjEwNDgxLCJlbWFpbCI6ImV4ZW1wbG9AZW1haWwuY29tIn0.YnZua_GjH8mE_nF2ts_52l6cHX24_GA3vt2h06aA2dg

# populate products

echo "populate "$SIZE_OF_PRODUCTS_TO_CREATE" products starting with id "$PRODUCT_START_ID" ..."

curl --location --request POST $HOST':8000/api/populate/2?size='$SIZE_OF_PRODUCTS_TO_CREATE'&startId='$PRODUCT_START_ID \
--header 'Authorization: Bearer '$TOKEN \
--header 'Cookie: adminer_permanent='

echo ""