Для запуска приложения измените в файле application.properties исходные данные на свои: url, username, password. 
В вашей url добавьте /my_db?createDatabaseIfNotExist=true , чтобы база данных автоматически создалось, где my_bd - имя бд.
После запуска перейдите по ссылке http://localhost:8080/addEmp , чтобы добавить сотрудников в базу данных.
