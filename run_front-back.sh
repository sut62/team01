#run front back
#cd front
#yarn serve
gnome-terminal --command="bash -c 'cd front; yarn serve; $SHELL'"
gnome-terminal --command="bash -c 'cd dinentity; ./mvnw spring-boot:run; $SHELL'"

