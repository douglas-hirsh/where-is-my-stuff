#!/bin/bash
# example
# ./deploy.sh example.com example douglas@example.com
domain=$1
appname=$2
email=$3
dbname=$2-mysql

mvn package
echo "java.runtime.version=11" > system.properties
[[ $? -eq 0 ]] && echo 'system.properties created'
echo "web: env SPRING_DATASOURCE_URL=\$JDBC_DATABASE_URL SPRING_JPA_HIBERNATE_DDL-AUTO=update SPRING_JPA_SHOW-SQL=true SERVER_PORT=\$PORT java -jar `ls target/*.jar`" > Procfile
[[ $? -eq 0 ]] && echo 'Procfile created'

ssh root@$domain bash <<setup_dokku
dokku plugin:install https://github.com/dokku/dokku-mysql.git mysql
dokku plugin:install https://github.com/dokku/dokku-letsencrypt.git
dokku apps:create $appname
dokku mysql:create $dbname
dokku mysql:link $dbname $appname
dokku domains:add $appname $domain
dokku domains:remove $appname $appname
setup_dokku

git remote add dokku dokku@$domain:$appname
[[ $? -eq 0 ]] && echo 'Dokku git remote created. Commit the Procfile and system.properties file and run git push dokku master.'
git add Procfile system.properties
git commit -m "feat: Add Procfile and system.properties for deployment"
git push dokku master

#Need to set email address
dokku config:set --no-restart $appname DOKKU_LETSENCRYPT_EMAIL=$email
ssh root@$domain bash <<setup_dokku
dokku letsencrypt $appname
dokku letsencrypt:auto-renew $appname
setup_dokku
