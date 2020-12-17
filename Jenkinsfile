pipeline {
    agent any

    parameters {
        choice(
            name:'compileTool',
            choices: ['Maven', 'Jenkins'],
            description: 'Seleccione herramienta de compilacion'
        )
    }

    stages {
        stage('pipeline') {
            steps {
                script {

                    switch(params.compileTool)
                    {
                        case 'gradle':
                            load 'gradle.groovy'
                        break;
                        case 'maven':
                            load 'maven.groovy'
                        break;
                    }
                }
            }
        }
    }
}