#!/bin/bash

readonly PROPERTY_FILE="./src/main/resources/application.properties"
readonly RED="\e[31m"
readonly GREEN="\e[32m"
readonly CYAN="\e[36m"
readonly NC="\e[0m"

run_sql_script() {
  if [ ! -f "$PROPERTY_FILE" ]; then
    echo -e "${RED}You must create a property file first${NC}"
    return
  fi

  db_localhost=$(get_property_value "url" | cut -d'/' -f3 | cut -d':' -f1)
  db_port=$(get_property_value "url" | cut -d'/' -f3 | cut -d':' -f2)
  db_username=$(get_property_value "user")
  db_password=$(get_property_value "password")

  my_sql_result=$(mysql -B --host="$db_localhost" --port="$db_port" --user="$db_username" --password="$db_password" <"$2" 2>&1 | grep -v "Using a password")

  echo "$1"

  if [ -z "$my_sql_result" ]; then
    echo -e "${GREEN}Success${NC}"
  else
    echo -e "${RED}$my_sql_result${NC}"
  fi
}

get_property_value() {
  if [ -f "$PROPERTY_FILE" ]; then
    (grep -w "$1" | cut -d= -f2) <"$PROPERTY_FILE"
  fi
}

set_properties() {
  # Db localhost
  get_localhost=$(get_property_value "url" | cut -d'/' -f3 | cut -d':' -f1)
  if [ -z "$get_localhost" ]; then
    db_localhost="127.0.0.1"
  else
    db_localhost="$get_localhost"
  fi
  read -r -e -i "$db_localhost" -p "$(echo -e "${CYAN}Db host: ${NC}")" input
  db_localhost="${input:-$db_localhost}"

  # Db port
  get_db_port=$(get_property_value "url" | cut -d'/' -f3 | cut -d':' -f2)
  if [ -z "$get_db_port" ]; then
    db_port="3306"
  else
    db_port="$get_db_port"
  fi
  read -r -e -i "$db_port" -p "$(echo -e "${CYAN}Db port: ${NC}")" input
  db_port="${input:-$db_port}"

  # Db username
  get_db_username=$(get_property_value "user")
  if [ -z "$get_db_username" ]; then
    db_username="root"
  else
    db_username="$get_db_username"
  fi
  read -r -e -i "$db_username" -p "$(echo -e "${CYAN}Db username: ${NC}")" input
  db_username="${input:-$db_username}"

  # Db password
  get_db_password=$(get_property_value "password")
  if [ -z "$get_db_password" ]; then
    db_password="root"
  else
    db_password="$get_db_password"
  fi
  read -r -e -i "$db_password" -p "$(echo -e "${CYAN}Db password: ${NC}")" input
  db_password="${input:-$db_password}"

  # Webserver port
  get_webserver_port=$(get_property_value "port")
  if [ -z "$get_webserver_port" ]; then
    webserver_port="8080"
  else
    webserver_port="$get_webserver_port"
  fi
  read -r -e -i "$webserver_port" -p "$(echo -e "${CYAN}Webserver port: ${NC}")" input
  webserver_port="${input:-$webserver_port}"

  echo "user=$db_username
password=$db_password
url=jdbc:mysql://$db_localhost:$db_port/stima
connection=LOCAL
port=$webserver_port" >"$PROPERTY_FILE"
}

# Create menu
echo -e "${CYAN}Installation:${NC}"
echo "1. Set properties"
echo "2. Install tables"
echo "3. Install tables and sample data"
echo "4. Exit"
echo -n "Enter your menu choice [1-4]: "

while :; do
  read -r choice

  case $choice in
  1)
    set_properties
    ;;

  2)
    run_sql_script "Installing database ..." "./src/main/resources/static/data/install.sql"
    ;;

  3)
    run_sql_script "Installing database ..." "./src/main/resources/static/data/install.sql"
    run_sql_script "Installing sample data ..." "./src/main/resources/static/data/sample-data.sql"
    ;;

  4)
    echo "Quitting ..."
    exit
    ;;

  *) echo "invalid option" ;;

  esac
  echo -n "Enter your menu choice [1-4]: "
done
