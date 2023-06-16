# bin/sh

# create categories

HOST=localhost
TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGFkbWluIiwicm9sZSI6IkFETUlOIiwiaXNzIjoiNkZNQUpCMG5QdVVVRjNCTnI5cm1vTmR5UDJqQ3YwM1MiLCJleHAiOjk5OTk5OTk5OTk5OSwiaWF0IjoxNjQ2NjEwNDgxLCJlbWFpbCI6ImV4ZW1wbG9AZW1haWwuY29tIn0.YnZua_GjH8mE_nF2ts_52l6cHX24_GA3vt2h06aA2dg

echo "populate categories ..."

curl --location --request PATCH $HOST':8000/api/categories/1/subcategories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '["COMPUTERS", "MOBILE", "COMPONENTS", "IMAGE & SOUND", "PERIPHERALS", "STORAGE", "NETWORK", "HEALTH", "HOUSE"]'

echo ""

curl --location --request PATCH $HOST':8000/api/categories/2/subcategories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '["SUBCAT1", "SUBCAT2"]'

echo ""

curl --location --request PATCH $HOST':8000/api/categories/3/subcategories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '["SUBCAT1", "SUBCAT2"]'

echo ""

curl --location --request PATCH $HOST':8000/api/categories/4/subcategories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '["SUBCAT1", "SUBCAT2"]'

echo ""

curl --location --request PATCH $HOST':8000/api/categories/5/subcategories' \
--header 'Authorization: Bearer '$TOKEN \
--header 'Content-Type: application/json' \
--data-raw '["SUBCAT1", "SUBCAT2"]'

echo ""