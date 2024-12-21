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
  }
}
</script>

<template>
  <div class="auth-page">
    <div v-if="showLogin">
      <LoginForm @switchToRegister="showLogin=false" />
    </div>
    <div v-else>
      <RegisterForm @switchToLogin="showLogin=true" />
    </div>
  </div>
</template>

<style scoped>

</style>