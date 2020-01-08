#run front back
#cd front
#yarn serve
gnome-terminal --command="bash -c 'cd frontend; yarn serve; $SHELL'"
gnome-terminal --command="bash -c 'cd backend; ./mvnw spring-boot:run; $SHELL'"

