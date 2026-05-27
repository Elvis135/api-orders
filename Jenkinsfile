pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Descargando código desde GitHub...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Compilando proyecto, ejecutando pruebas y generando reporte JaCoCo...'
                script {
                    if (isUnix()) {
                        sh 'chmod +x mvnw'
                        sh './mvnw clean verify'
                    } else {
                        bat '.\\mvnw.cmd clean verify'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Enviando métricas a SonarQube...'
                script {
                    withSonarQubeEnv('sonarqube') {
                        if (isUnix()) {
                            sh './mvnw sonar:sonar -Dsonar.projectKey=api-orders -Dsonar.projectName=api-orders -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                        } else {
                            bat '.\\mvnw.cmd sonar:sonar -Dsonar.projectKey=api-orders -Dsonar.projectName=api-orders -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                        }
                    }
                }
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                echo 'Construyendo imagen Docker y desplegando contenedor...'
                script {
                    if (isUnix()) {
                        sh 'docker build -t api-orders .'
                        sh 'docker rm -f api-orders-container || true'
                        sh 'docker run -d --name api-orders-container -p 8082:8080 api-orders'
                    } else {
                        bat 'docker build -t api-orders .'
                        bat 'docker rm -f api-orders-container || exit /b 0'
                        bat 'docker run -d --name api-orders-container -p 8082:8080 api-orders'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline finalizado correctamente: Build, Test, SonarQube y Docker OK.'
        }

        failure {
            echo 'El pipeline falló. Revisar la consola de Jenkins.'
        }
    }
}