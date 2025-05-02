FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN apt-get update && \
    apt-get install -y python3 python3-pip ant && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . .

CMD ["/bin/bash"]
