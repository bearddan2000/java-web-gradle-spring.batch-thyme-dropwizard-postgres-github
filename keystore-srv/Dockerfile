FROM openjdk:17-alpine

WORKDIR /keystore

RUN $JAVA_HOME/bin/keytool -genkeypair \
-dname "cn=myname, ou=mygroup, o=mycompany, c=mycountry" \
-alias baeldung -keyalg RSA -keysize 2048 \
-storetype PKCS12 \
-keystore /keystore/baeldung.p12 \
-storepass password -validity 3650

RUN chmod -R +x * && ls

CMD ["pwd"]
