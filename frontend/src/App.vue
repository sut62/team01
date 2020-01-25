<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" app>
      <template v-if="isSignin">
        <template v-if="user.studentId">
          <v-list dense>
            <template v-for="item in items">
              <v-list-item :key="item.text" link @click="item.click" :disabled="item.disabled">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>{{ item.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </template>
        <template v-else>
          <v-list dense>
            <template v-for="item in staffItems">
              <v-row v-if="item.heading" :key="item.heading" align="center">
                <v-col cols="6">
                  <v-subheader v-if="item.heading">{{ item.heading }}</v-subheader>
                </v-col>
                <v-col cols="6" class="text-center">
                  <a href="#!" class="body-2 black--text">EDIT</a>
                </v-col>
              </v-row>
              <v-list-group
                v-else-if="item.children"
                :key="item.text"
                v-model="item.model"
                :prepend-icon="item.model ? item.icon : item['icon-alt']"
                append-icon
              >
                <template v-slot:activator>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.text }}</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
                <v-list-item v-for="(child, i) in item.children" :key="i" link>
                  <v-list-item-action v-if="child.icon">
                    <v-icon>{{ child.icon }}</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>{{ child.text }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-item v-else :key="item.text" link @click="item.click">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>{{ item.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </template>
      </template>
      <template v-else>
        <v-list dense>
          <v-list-item @click="handleIndex">
            <v-list-item-action>
              <v-icon>mdi-home</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>หน้าแรก</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </template>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      :color="appBarColor()"
      :dark="user ? true : false"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-btn icon large>
        <v-avatar size="32px" item>
          <v-img src="https://cdn.vuetifyjs.com/images/logos/logo.svg" alt="Vuetify" />
        </v-avatar>
      </v-btn>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <span class="hidden-sm-and-down">SUT Dormitory</span>
      </v-toolbar-title>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Search"
        class="hidden-sm-and-down"
      />
      <v-spacer />
      <v-btn icon>
        <v-icon>mdi-apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-bell</v-icon>
      </v-btn>
      <template v-if="isSignin">
        <span>Sign in as</span>
        <template v-if="user.studentId">
          <v-btn text @click="handleProfile">{{ user.fullName }}</v-btn>
        </template>
        <template v-else>
          <v-btn text @click="handleProfile">{{ user.name }}</v-btn>
        </template>

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="handleSignout">
              <v-list-item-title>
                <v-icon>mdi-logout</v-icon>Sign out
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </template>
      <template v-else>
        <v-btn text to="/signin">Sign in</v-btn>
      </template>
    </v-app-bar>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <router-view></router-view>
        </v-row>
      </v-container>
    </v-content>
    <v-footer :color="footerColor()" :dark="isDark()" app>
      <div>&copy; SUT62 TEAM01</div>
      <v-divider vertical></v-divider>
      <div>
        <v-btn href="https://github.com/sut62/team01" icon small target="_blank" v-on="on">
          <v-icon>mdi-github-circle</v-icon>
        </v-btn>
      </div>
      <div>
        <v-btn
          href="https://www.facebook.com/Sut-Se62-Team01-118308956295583"
          icon
          small
          target="_blank"
          v-on="on"
        >
          <v-icon>mdi-facebook</v-icon>
        </v-btn>
      </div>

      <v-spacer></v-spacer>
    </v-footer>
  </v-app>
</template>
<script>
import api from "../src/Api";
export default {
  name: "App",
  components: {},
  data() {
    return {
      themes: {
        student: {
          appBar: "blue darken-3",
          fontColor: "light"
        },
        staff: {
          appBar: "amber darken-3",
          fontColor: "light"
        },
        public: {
          appBar: "white",
          fontColor: "dark"
        }
      },
      isSignin: undefined,
      userRole: null,
      user: {},
      dialog: false,
      drawer: null,
      staffItems: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/home");
          }
        },
        {
          icon: "mdi-contacts",
          role: "staff",
          text: "ลงทะเบียนยานพาหนะ",
          click: () => {
            this.$router.push("/staff/enrollvehicle");
          }
        },
        {
          icon: "mdi-alien",
          role: "staff",
          text: "ลงทะเบียนเครื่องใช้ไฟฟ้า",
          click: () => {
            this.$router.push("/staff/electricregister");
          }
        },
        {
          icon: "mdi-package",
          role: "staff",
          text: "จัดการพัสดุ",
          click: () => {
            this.$router.push("/staff/packageManagement");
          }
        },
        {
          icon: "mdi-email",
          role: "staff",
          text: "ข้อมูลการแจ้งซ่อม",
          click: () => {
            this.$router.push("/staff/RepairDetail");
          }
        },
        {
          icon: "mdi-bicycle",
          role: "staff",
          text: "อนุมัติคำร้องขอยืมรถจักรยาน",
          click: () => {
            this.$router.push("/staff/approve/borrowedbike");
          }
        },
        {
          icon: "mdi-rocket",
          role: "staff",
          text: "แสดงข้อมูลเครื่องไฟฟ้า",
          click: () => {
            this.$router.push("/staff/showElectric");
          }
        },
        {
          icon: "mdi-checkbox-marked",
          role: "staff",
          text: "บันทึกสถานะพัสดุ",
          click: () => {
            this.$router.push("/staff/confirmPackage");
          }
        },
        {
          icon: "mdi-car-info",
          role: "staff",
          text: "ค้นหาข้อมูลยานพาหนะ",
          click: () => {
            this.$router.push("/staff/searchEnrolledVehicle");
          }
        },
      ],
      items: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/home");
          }
        },
        {
          icon: "mdi-contacts",
          role: "student",
          text: "จองห้องพัก",
          click: () => {
            this.$router.push("/roombooking");
          }
        },
        {
          icon: "mdi-bicycle-basket",
          role: "student",
          text: "ยืมจักรยาน",
          click: () => {
            this.$router.push("/borrowedBike");
          }
        },
        {
          icon: "mdi-contacts",
          role: "student",
          text: "แจ้งซ่อม",
          click: () => {
            this.$router.push("/repair");
          }
        },
        {
          icon: "mdi-help-circle",
          role: "student",
          text: "สถานะห้องพัก",
          click: () => {
            this.$router.push("/roomstatus");
          }
        }
      ]
    };
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    if (this.user) {
      this.isSignin = true;
      if (this.user.studentId) {
        this.userRole = "student";
        this.checkStudentAlreadyBookingRoom();
      } else {
        this.userRole = "staff";
      }
    } else {
      this.isSignin = false;
    }
  },
  methods: {
    checkStudentAlreadyBookingRoom() {
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        student_id: user.id
      };
      api
        .post("/api/roombooking/student", JSON.stringify(body))
        .then(res => {
          if (res.data.length > 0) {
            this.items[1].click = () => {
              alert("นักศึกษาจองห้องไปแล้ว");
            };
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    whichColor() {
      if (!this.userRole) {
        return "white";
      } else {
        return "dark";
      }
    },
    isDark() {
      if (this.isSignin) return true;
      else return false;
    },
    appBarColor() {
      if (this.userRole == "staff") {
        return "amber darken-3";
      } else if (this.userRole == "student") {
        return "blue darken-3";
      } else {
        return "white";
      }
    },
    footerColor() {
      if (this.userRole == "staff") {
        return "amber darken-4";
      } else if (this.userRole == "student") {
        return "primary";
      } else {
        return "white";
      }
    },
    handleProfile() {
      this.$router.push("/profile");
    },
    handleSignout() {
      this.isSignin = false;
      localStorage.removeItem("user");
      this.$router.go("/");
    },
    handleIndex() {
      this.$router.push("/");
    }
  }
};
</script>
