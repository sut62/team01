<template>
  <v-content>
    <v-form
      ref="form"
    >
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-card class="elevation-12">
            <v-toolbar color="deep-purple" light flat>
            
              <v-icon dark>mdi-wrench</v-icon>&nbsp;&nbsp;
              
               <span class="white--text">
              <v-toolbar-title>ระบบแจ้งซ่อมหอพัก</v-toolbar-title>
              </span>
              <div class="flex-grow-1"></div>
            </v-toolbar>
            <v-card-text>
              <v-select
                v-model="selectedStudent"
                :items="students"
                item-text="stdName"
                item-value="id"
                label="เลือกชื่อนักศึกษา"
                :rules="[(v) => !!v || 'กรุณาเลือกชื่อนักศึกษา']"
                required
              ></v-select>

              
              <v-select
                v-model="selectedDeviceType"
                :items="types"
                item-text="type"
                item-value="id"
                label="เลือกประเภทงาน"
                :rules="[(v) => !!v || 'กรุณาเลือกประเภทงาน']"
                required
              ></v-select>

              <v-select
                v-model="selectedDeviceProblem"
                :items="problems"
                item-text="problem"
                item-value="id"
                label="เลือกชื่ออุปกรณ์ที่ชำรุด"
                :rules="[(v) => !!v || 'กรุณาเลือกชื่ออุปกรณ์ที่ชำรุด']"
                required
              ></v-select>

              <v-text-field
                v-model="insertList"
                :items="titles"
                item-text="name"
                item-value="id"
                label="ระบุอาการ/ปัญหา"
                :rules="[(v) => !!v || 'กรุณาระบุอาการ/ปัญหา']"
                required
              ></v-text-field>

              

              <div class="text-center">
                <v-btn class="mr-3" color="warning" @click="Saveshow">แจ้ง</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
  </v-content>
</template>
 <script>
import api from "../Api.js";
export default {
  props: {
    student: {}
  },
  data() {
    return {
      insertList: undefined,
      selectedStudent: null,
      selectedDeviceType: null,
      selectedDeviceProblem: null,
      titles: [],
      students: [],
      types: [],
      problems: []
    };
  },
  mounted() {
    //ประกาศฟังก์ชันที่ต้องการดึงข้อมูลจากหลังบ้านมาแสดงใน combobox แต่ละตัว
    this.getAllStudents();
    this.getAllDeviceTypes();
    this.getAllDeviceProblems();
  },
  methods: {
    Resetshow() {
      this.$refs.form.reset();
    },
      Save() {
      //เมือกดปุ่มบันทึก ขณะที่ยังกรอกข้อมูลไม่ครบ ระบบจะแจ้งเตื่อน "กรุณากรอรกข้อมูลให้ครบ"
      if (
        !this.insertList ||
        !this.selectedStudent ||
        !this.selectedDeviceType||
        !this.selectedDeviceProblem
      ) {
        alert("กรุณากรอกข้อมูลให้ครบ!");
         
      } else {
        this.SaveRepair();
      }
    },

    SaveRepair() {
    
      api
        .post(
          "/Repair/" +
            this.selectedStudent  +
            "/" +
            this.selectedDeviceType +
            "/" +
            this.selectedDeviceProblem +
            "/" +
            this.insertList
        )
        .then(response => {
          alert("บันทึกข้อมูลสำเร็จ!");
          console.log(JSON.parse(JSON.stringify(response.data)));
          this.insertList= null;
          this.selectedStudent = null;
          this.selectedDeviceType = null;
          this.selectedDeviceProblem = null;
           this.Resetshow();
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllStudents() {
      api
        .get("/roomBookings")
        .then(response => {
          this.students = response.data;
          console.log("ดึงข้อมูล Student สำเร็จ");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllDeviceTypes() {
      api
        .get("/DeviceTypes")
        .then(response => {
          this.types = response.data;
          console.log("เพิ่มข้อมูล DeviceType แล้ว");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllDeviceProblems() {
      api
        .get("/DeviceProblems")
        .then(response => {
          this.problems = response.data;
          console.log("ดึงข้อมูล Problem สำเร็จ");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>