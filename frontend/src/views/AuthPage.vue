<script>
import LoginForm from "@/components/LoginForm.vue";
import RegisterForm from "@/components/RegisterForm.vue";

export default {
  name: "AuthPage",
  components: {
    LoginForm,
    RegisterForm
  },
  data() {
    return {
      showLogin: true
    }
  },
  mounted() {
    const token = localStorage.getItem("token");

    if (token && this.$route.path === "/") {
      this.$router.push("/main");
    }
  }
}
</script>

<template>
  <div class="auth-page">
    <div v-if="showLogin">
      <LoginForm @switchToRegister="showLogin=false"/>
    </div>
    <div v-else>
      <RegisterForm @switchToLogin="showLogin=true"/>
    </div>
  </div>
</template>

<style scoped>
.auth-page {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.auth-page > div {
  position: relative;
  z-index: 2;
  padding: 30px;
  border-radius: 12px;
  color: #fff;
  width: 100%;
  max-width: 400px;
  text-align: center;
}

</style>