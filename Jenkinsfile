pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/cinebh-backend:latest'
        SERVER_PORT = '8081'
    }

    stages {
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

        stage('Deploy Container') {
            steps {
                echo "Deploying backend container on port ${SERVER_PORT}"
                sh """
                docker run -d --name cinebh-backend -p ${SERVER_PORT}:8080 ${BACKEND_IMAGE}
                """
            }
        }
    }

    post {
        success {
            echo "Backend build, push, and deployment completed successfully!"
        }
        failure {
            echo "Backend build or deployment failed."
        }
    }
}
