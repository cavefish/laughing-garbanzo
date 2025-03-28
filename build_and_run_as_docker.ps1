docker build --tag laughing-garbanzo .

docker run --rm `
    --name minipost `
    --link mysql `
    -p 8080:8080 `
    -e DBCONN="jdbc:mysql://mysql:3306/myDb?createDatabaseIfNotExist=true" `
    -e DBUSER="root" `
    -e DBPASS="secret" `
    laughing-garbanzo
