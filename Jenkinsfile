pipeline {
  agent { dockerfile true }
  environment {
    REGISTRY = 'Docker-registry'
    IMAGE_NAME = 'ecommerce-app'
  }
  stages {
    stage('checkout'){
      steps {
        git 'https://github.com/trilionit/ecommerce.git'
      }
    }
    stage('Build'){
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test'){
      steps {
        sh 'mvn test'
      }
    }
    stage('Build Docker Image'){
      steps {
        script {
          docker.build("${REGISTRY}/${IMAGE_NAME}:latest")
        }
      }
    }
    stage('Deploy'){
      steps {
        script {
          docker.withRegistry("https://${REGISTRY}", 'docker_credentials-id'){
            sh "docker push ${REGISTRY}/${IMAGE_NAME}:latest"
            // DEPLOY TO SERVER (SSH OR ANY CLOUD PROVIDER)
            // sh ""
            ssh ec2-user@ec2-98-82-182-11.compute-1.amazonaws.com "docker pull ${REGISTRY}/${IMAGE_NAME}:latest && docker compose up -d"
          }
        }
      }
    }
  }
  post {
    success {
      echo "Deployment successful"
    }
    failure {
      echo "Deployment failed"
    }
  }
}