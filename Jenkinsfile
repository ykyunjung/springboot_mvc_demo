pipeline {
    agent { label 'jenkinslave' }
    stages {
        stage('Build') { 
            steps {
                sh "mvn install -DskipTests" 
            }
        }
        stage('Build docker image') {
        /* This stage builds the actual image; synonymous to
           docker build on the command line */
            steps {
            sh "sudo docker build . -t javasample:1"
            }    
        }
        stage('Test image') {
         /* This stage runs unit tests on the image; we are
            just running dummy tests here */
            steps {
                sh 'echo "Tests successful"'
          }
        }
        stage('Push image to OCIR') {
         /* Final stage of build; Push the 
            docker image to our OCI private Registry*/
        steps {
            sh "sudo docker login -u ${params.REGISTRY_USERNAME} -p '${params.REGISTRY_TOKEN}' ${params.DOCKER_REGISTRY}"
            sh "sudo docker tag javasample:1 ${params.DOCKER_REGISTRY}/${params.DOCKER_REPO}:custom"
            sh "sudo docker push ${params.DOCKER_REGISTRY}/${params.DOCKER_REPO}:custom"
           }
         }
