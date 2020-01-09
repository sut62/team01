<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
    >
      <template v-if="isSignin">
        <template v-if="user.studentId">
          <v-list dense>
            <template v-for="item in items">
              <v-row v-if="item.heading" :key="item.heading" align="center">
                <v-col cols="6">
                  <v-subheader v-if="item.heading">
                    {{ item.heading }}
                  </v-subheader>
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
                append-icon=""
              >
                <template v-slot:activator>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        {{ item.text }}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
                <v-list-item v-for="(child, i) in item.children" :key="i" link>
                  <v-list-item-action v-if="child.icon">
                    <v-icon>{{ child.icon }}</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>
                      {{ child.text }}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-item v-else :key="item.text" link @click="item.click">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ item.text }}
                  </v-list-item-title>
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
                  <v-subheader v-if="item.heading">
                    {{ item.heading }}
                  </v-subheader>
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
                append-icon=""
              >
                <template v-slot:activator>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        {{ item.text }}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
                <v-list-item v-for="(child, i) in item.children" :key="i" link>
                  <v-list-item-action v-if="child.icon">
                    <v-icon>{{ child.icon }}</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>
                      {{ child.text }}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-item v-else :key="item.text" link @click="item.click">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ item.text }}
                  </v-list-item-title>
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
              <v-icon>mdi-home </v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>
                หน้าแรก
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </template>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="blue darken-3"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-btn icon large>
        <v-avatar size="32px" item>
          <v-img
            src="https://cdn.vuetifyjs.com/images/logos/logo.svg"
            alt="Vuetify"
        /></v-avatar>
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
        <template v-if="user.studentId">
          <v-btn text @click="handleProfile">
            {{ user.fullName }}
          </v-btn>
        </template>
        <template v-else>
          <v-btn text @click="handleProfile">
            {{ user.name }}
          </v-btn>
        </template>

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="handleSignout">
              <v-list-item-title>Sign out</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </template>
      <template v-else>
        <v-btn text to="/signin"> Sign in </v-btn>
      </template>
    </v-app-bar>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <router-view></router-view>
        </v-row>
      </v-container>
    </v-content>
    <!-- <v-btn bottom color="pink" dark fab fixed right @click="dialog = !dialog">
      <v-icon>mdi-plus</v-icon>
    </v-btn> -->
  </v-app>
</template>
<script>
// import HelloWorld from './components/HelloWorld';
// import Navbar from './components/Navbar'
export default {
  name: "App",
  components: {},
  data() {
    return {
      isSignin: undefined,
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
          icon: "mdi-contacts",
          role: "staff",
          text: "ลงทะเบียนเครื่องใช้ไฟฟ้า",
          click: () => {
            this.$router.push("/staff/electricregister");
          }
        }
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
          icon: "mdi-contacts",
          role: "student",
          text: "ยืมจักรยาน",
          click: () => {
            this.$router.push("/borrowedBike");
          }
        }

        // { icon: "mdi-history", text: "Frequently contacted" },
        // { icon: "mdi-content-copy", text: "Duplicates" },
        // {
        //   icon: "mdi-chevron-up",
        //   "icon-alt": "mdi-chevron-down",
        //   text: "Labels",
        //   model: true,
        //   children: [{ icon: "mdi-plus", text: "Create label" }]
        // },
        // {
        //   icon: "mdi-chevron-up",
        //   "icon-alt": "mdi-chevron-down",
        //   text: "More",
        //   model: false,
        //   children: [
        //     { text: "Import" },
        //     { text: "Export" },
        //     { text: "Print" },
        //     { text: "Undo changes" },
        //     { text: "Other contacts" }
        //   ]
        // },
        // { icon: "mdi-settings", text: "Settings" },
        // { icon: "mdi-message", text: "Send feedback" },
        // { icon: "mdi-help-circle", text: "Help" },
        // { icon: "mdi-cellphone-link", text: "App downloads" },
        // { icon: "mdi-keyboard", text: "Go to the old version" }
      ]
    };
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    if (this.user) {
      this.isSignin = true;
    } else {
      this.isSignin = false;
    }
  },
  methods: {
    handleProfile() {
      this.$router.push("/profile");
    },
    handleSignout() {
      if (confirm("Do you want to sign out?")) {
        this.isSignin = false;
        localStorage.removeItem("user");
        this.$router.go("/");
      }
    },
    handleIndex() {
      this.$router.push("/");
    }
  }
};
</script>
