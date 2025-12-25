pipeline {
  agent any

  stages {

    stage('Checkout') {
      steps {
        checkout scm
        sh 'pwd && ls -la'
        sh 'ls -la demo'
      }
    }

    stage('Build & Test (demo)') {
      steps {
        dir('demo') {
          sh '''
            set -e

            # Windows kaynaklı CRLF ihtimaline karşı
            sed -i 's/\\r$//' mvnw || true

            chmod +x mvnw
            ./mvnw -B clean test
          '''
        }
      }
    }

    stage('Deploy') {
      when {
        expression { currentBuild.currentResult == 'SUCCESS' }
      }
      steps {
        echo 'Deploy aşaması çalışıyor...'
        sh 'echo "DEPLOY OK"' // şimdilik dummy
      }
    }
  }

  post {
    failure {
      echo '❌ Pipeline fail oldu'
    }
    success {
      echo '✅ Testler geçti, deploy başarılı'
    }
  }
}
