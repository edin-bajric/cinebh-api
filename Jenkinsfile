pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/cinebh-backend:latest'
        SERVER_PORT = '8081'

        # PostgreSQL environment variables
        POSTGRES_IMAGE = 'postgres:latest'
        POSTGRES_CONTAINER = 'postgres-cinebh'
        POSTGRES_PORT = '5432'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'edin'
        POSTGRES_DB = 'postgres'
       
        # Database connection URL for backend
        DB_URL = "jdbc:postgresql://localhost:${POSTGRES_PORT}/${POSTGRES_DB}?currentSchema=cinebh"
    }

    stages {
        stage('Deploy PostgreSQL') {
            steps {
                echo "Deploying PostgreSQL database"
                sh """
                docker rm -f ${POSTGRES_CONTAINER} || true
                docker run -d --name ${POSTGRES_CONTAINER} \
                    -e POSTGRES_USER=${POSTGRES_USER} \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
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
                    -p ${SERVER_PORT}:8080 \
                    -e DB_URL=${DB_URL} \
                    -e DB_USERNAME=${POSTGRES_USER} \
                    -e DB_PASSWORD=${POSTGRES_PASSWORD} \
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
