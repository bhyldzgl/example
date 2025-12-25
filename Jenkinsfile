pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        sh 'ls -la'
      }
    }

    stage('Build & Test (Maven Wrapper)') {
      steps {
        sh '''
          set -e
          if [ ! -f mvnw ]; then
            echo "ERROR: mvnw dosyası repo kökünde yok."
            exit 1
          fi

          # CRLF -> LF (Windows kaynaklı sorunlara karşı)
          sed -i 's/\\r$//' mvnw || true

          chmod +x mvnw
          ./mvnw -B clean test
        '''
      }
    }

    stage('Deploy') {
      when { expression { currentBuild.currentResult == 'SUCCESS' } }
      steps {
        echo 'Deploy aşaması çalışıyor...'
        sh 'echo "DEPLOY OK"'
      }
    }
  }

  post {
    failure { echo '❌ Pipeline fail oldu' }
    success { echo '✅ Testler geçti, deploy başarılı' }
  }
}
