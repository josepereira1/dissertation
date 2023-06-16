# bin/sh

HOST=localhost
SIZE_OF_PRODUCTS_TO_ADD=250
PRODUCT_START_ID=0
TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGFkbWluIiwicm9sZSI6IkFETUlOIiwiaXNzIjoiNkZNQUpCMG5QdVVVRjNCTnI5cm1vTmR5UDJqQ3YwM1MiLCJleHAiOjk5OTk5OTk5OTk5OSwiaWF0IjoxNjQ2NjEwNDgxLCJlbWFpbCI6ImV4ZW1wbG9AZW1haWwuY29tIn0.YnZua_GjH8mE_nF2ts_52l6cHX24_GA3vt2h06aA2dg

echo "populate "$SIZE_OF_PRODUCTS_TO_ADD" products in all categories, starting with product id "$PRODUCT_START_ID" ..."

curl --location --request POST $HOST':8000/api/populate/3?size='$SIZE_OF_PRODUCTS_TO_ADD'&startId='$PRODUCT_START_ID \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--header 'Cookie: adminer_permanent=' \
--data-raw '[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18]'

echo ""