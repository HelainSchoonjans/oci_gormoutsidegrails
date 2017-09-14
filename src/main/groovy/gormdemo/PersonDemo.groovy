package gormdemo

import groovy.util.logging.Slf4j
import org.grails.orm.hibernate.HibernateDatastore

@Slf4j
class PersonDemo {

    static void main(args) {
        Map configuration = [
                'hibernate.hbm2ddl.auto': 'create-drop',
                'dataSource.url'        : 'jdbc:h2:mem:myDB'
        ]
        new HibernateDatastore(configuration, Person)

        Person.withNewSession {
            [[firstName: 'Robert', lastName: 'Fripp'],
             [firstName: 'Ritchie', lastName: 'Blackmore'],
             [firstName: 'Jeff', lastName: 'Beck'],
             [firstName: 'Eric', lastName: 'Clapton'],
             [firstName: 'David', lastName: 'Gilmour'],
             [firstName: 'Randy', lastName: 'Rhoads']].each {
                new Person(it).save()
            }

            def people = Person.list()
            for (Person p : people) {
                log.info "${p.lastName}, ${p.firstName}"
            }
        }
    }
}