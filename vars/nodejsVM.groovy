//oka centralised pipeline rastunam , e pipeline dheniki work avuthadi any nodejs project which is going to be deployed into the VM.
//nodejsVM.groovy (nodejs programming language ki VM lo deploy chese application ki e pipeline annedi work avuthundhi)
pipeline {
    agent {
        node {
            label 'AGENT-1'
        }
    }
    environment { 
        packageVesion = ''
        nexusURL = '172.31.37.164:8081'
    }
    options {
        timeout(time: 1, unit: 'HOURS')
        disableConcurrentBuilds()
    }
    parameters {
    //     string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

    //     text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'Deploy', defaultValue: false, description: 'Toggle this value')

        // choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        // password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    // build
    stages {
        stage('Get the version') {
            steps {
                script{
                    def packageJson = readJSON file: 'package.json'
                    packageVesion = packageJson.version
                    echo "application version is $packageVesion"
                }
            }
        }
        
    }
    // Post build means build ipoena tharuwatha em cheyali
    post { 
        always { 
            echo 'I will always say Hello again!'
            deleteDir()
        }
        failure { 
            echo 'This runs when pipeline is failed , used generally to send some alerts !'
        }
        success { 
            echo 'I will say Hello when pipeline is success !'
        }
    }
}