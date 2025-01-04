pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/cinebh-backend:latest'
        SERVER_PORT = '8081'

        POSTGRES_IMAGE = 'postgres:latest'
        POSTGRES_CONTAINER = 'postgres-cinebh'
        POSTGRES_PORT = '5432'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'edin'
        POSTGRES_DB = 'cinebh'

        DB_URL = "jdbc:postgresql://postgres-cinebh:${POSTGRES_PORT}/${POSTGRES_DB}"
        DOCKER_NETWORK = 'cinebh-network'

        MG_DOMAIN = 'https://api.eu.mailgun.net/v3/mg.edinbajric.me'
        MG_FROM_EMAIL = 'Cinebh Ticketing System <mailgun@mg.edinbajric.me>'
        MG_PASSWORD = 'd8232d2e0cd6ecc5e1175e32efe5802d-c02fd0ba-f0398430'
    }

    stages {
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network if not exists"
                sh """
                docker network inspect ${DOCKER_NETWORK} >/dev/null 2>&1 || \
                docker network create ${DOCKER_NETWORK}
                """
            }
        }

        stage('Deploy PostgreSQL') {
            steps {
                echo "Deploying PostgreSQL database"
                sh """
                docker rm -f ${POSTGRES_CONTAINER} || true
                docker run -d --name ${POSTGRES_CONTAINER} \
                    --network ${DOCKER_NETWORK} \
                    -e POSTGRES_USER=${POSTGRES_USER} \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                    -e POSTGRES_DB=${POSTGRES_DB} \
                    -v postgres_data:/var/lib/postgresql/data \
                    -p ${POSTGRES_PORT}:5432 \
                    ${POSTGRES_IMAGE}
                """
            }
        }

        stage('Checkout') {
            steps {
                echo "Cloning backend repository"
                git branch: 'dev',
                    url: 'https://github.com/edin-bajric/cinebh-api.git',
                    credentialsId: 'gh-token'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building backend Docker image"
                sh """
                docker build -t ${BACKEND_IMAGE} .
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                echo "Pushing backend Docker image to Docker Hub"
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh "docker push ${BACKEND_IMAGE}"
                }
            }
        }

        stage('Deploy Backend Container') {
            steps {
                echo "Deploying backend container on port ${SERVER_PORT}"
                sh """
                docker rm -f cinebh-backend || true
                docker run -d --name cinebh-backend \
                    --network ${DOCKER_NETWORK} \
                    -p ${SERVER_PORT}:8080 \
                    -e DB_URL=${DB_URL} \
                    -e DB_USERNAME=${POSTGRES_USER} \
                    -e DB_PASSWORD=${POSTGRES_PASSWORD} \
                    -e MG_DOMAIN=${MG_DOMAIN} \
                    -e MG_FROM_EMAIL="${MG_FROM_EMAIL}" \
                    -e MG_PASSWORD=${MG_PASSWORD} \
                    ${BACKEND_IMAGE}
                """
            }
        }
    }

    post {
        success {
            echo "Backend and database deployment completed successfully!"
        }
        failure {
            echo "Deployment failed. Check the logs for details."
        }
    }
}
