<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Search Enrolled Vehicle</h1>
        <br>
        
        <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="3">
            <v-text-field solo 
            label="กรอกยี่ห้อยานพาหนะ"
            v-model="bnd_Name"
            @keyup.enter="searchEnrolledVehicles"/>
            </v-col>
            <v-col cols="1">
                <v-btn @click="searchEnrolledVehicles" large class="light-green accent-4">Search</v-btn>
            </v-col>
        </v-row>

        <!-- <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="3">
            <v-text-field solo 
            label="กรอกหมายเลขห้อง"
            v-model="bnd_Name"
            @keyup.enter="getStudentName"/>
            </v-col>
            <v-col cols="1">
                <v-btn @click="s" large class="light-green accent-4">Search</v-btn>
            </v-col>
        </v-row> -->

        <!-- <v-row justify="center">
        <v-col cols="10">
            <v-data-table :headers="headers" :items="List_BndName" :items-per-page="5" class="elevation-3"></v-data-table>
        </v-col>
        </v-row> -->

        <v-card>
            <v-data-table
            :headers="headers"
            :items="lst_BndName"
            :items-per-page="5"
            ></v-data-table>
        </v-card>
    
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
import api from "../Api.js";
export default {
    data() {
        return {
            headers: [
                {
                text: "ID",
                align: "left",
                sortable: false,
                value: "id"
                },
                { text: "Name", value: "enrolledStudents.student.fullName"},
                { text: "Date Enroll", value: "enrollDate" },
                { text: "Licnse Plate", value: "licensePlate" },
                { text: "Brand Name", value: "brandName" },
                { text: "Other Details", value: "otherDetails" },
            ],
            lst_lcPlate: [],
            lst_BndName: [],
            items: [],
            lc_Plate: undefined,
            bnd_Name: undefined,
        }
    },
    mounted() {
        // this.searchEnrolledVehicles();
    },
    methods: {
        searchEnrolledVehicles() {
            api
            .get("/api/enrolledVehicles/searchBrandName=" + this.bnd_Name)
            .then(response  => {
                this.lst_BndName = response.data;
                console.log(JSON.parse(JSON.stringify(response.data)));
            })
            .catch(e => {
                console.log("Error in searchEnrolledVehicle() :" + e);
            });
        }
    }
}
</script>