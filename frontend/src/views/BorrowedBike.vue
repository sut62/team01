<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>ยืมจักรยาน</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-select
              v-model="selectedStudent"
              :items="students"
              item-text="username"
              item-value="id"
              label="เลือกนักศึกษา"
              outlined
            ></v-select>
            <v-select
              v-model="selectedBikeTypes"
              :items="bikeTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทจักรยาน"
              outlined
            ></v-select>
            <v-select
              v-model="selectedDateType"
              :items="dateTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทวันยืม"
              outlined
            ></v-select>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="primary" @click="handleBorrowBike">ยืนยัน</v-btn>
          <v-spacer />
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "borrowedBike",
  data() {
    return {
      students: [],
      bikeTypes: [],
      dateTypes: [],
      selectedStudent: undefined,
      selectedBikeTypes: undefined,
      selectedDateType: undefined
    };
  },
  methods: {
    handleBorrowBike() {
      // alert("selectedStudent = " + JSON.stringify(this.selectedStudent)
      // + "\nselectedBikeTypes = " + JSON.stringify(this.selectedBikeTypes));
      if (confirm("Do you want to continue?")) {
        let body = {
          bikeType_id: this.selectedBikeTypes,
          student_id: this.selectedStudent,
          dateType_id: this.selectedDateType
        };
        api
          .post("/api/borrowedbike", body)
          .then(() => {
            alert("บันทึกข้อมูลการแจ้งยืมเรียบร้อย.");
            this.$router.go();
          })
          .catch(e => {
            alert(e);
          });
      }
    },
    getStudentDetail() {
      this.students.push(JSON.parse(localStorage.getItem("user")));
    },
    getAllBikeTypes() {
      api
        .get("/api/biketypes")
        .then(res => {
          this.bikeTypes = res.data;
          // console.log(res.data)
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllDateTypes() {
      api
        .get("/api/datetypes")
        .then(res => {
          this.dateTypes = res.data;
          // console.log(res.data)
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getStudentDetail();
    this.getAllBikeTypes();
    this.getAllDateTypes();
  }
};
</script>

<style></style>
