pipeline {
 agent any 
 stages {
	stage('Build Imagem Docker! ') {
		steps {
			sh 'cp /var/lib/jenkins/workspace/AlarumFront/target/AlarumAdmin-1.0-SNAPSHOT.war /home/senai/docker/'
			sh 'docker image build -t AlarumFront/tomcat /home/senai/docker/'
		}
	}
	stage('Remove') {
		steps {
			//sh 'docker container stop trabalho-sidnei'
			echo 'Etapa 2'
			sh 'docker container rm -f $(docker container ls -aq)'
		}
	}
	stage('Executar') {
		steps {
			sh 'docker container run -d --name AlarumFront --publish 8081:8080 -it AlarumFront/tomcat'
		}
	}
	stage('Remover Workspace') {
		steps {
			//sh 'sudo rm -f --recursive -r /var/lib/jenkins/workspace/trabalho-sidnei_master'
			sh 'rm -f /home/senai/docker/AlarumAdmin-1.0-SNAPSHOT.war'
		}
	}
 }
}