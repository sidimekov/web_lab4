<script>
import ErrorMessage from "@/components/ErrorMessage.vue";

export default {
  name: "LoginForm",
  components: {ErrorMessage},

  data() {
    return {
      login: "",
      password: ""
    }
  },

  methods: {
    async handleLogin() {
      try {
        const resp = await fetch("http://localhost:8080/api/users/login", {
          method: "POST",
          body: JSON.stringify({login: this.login, password: this.password}),
          headers: {
            "Content-Type": "application/json"
          }
        });

        if (resp.ok) {
          const data = await resp.json();
          const token = data.token;
          localStorage.setItem("token", token);
          window.location.replace("/main");
          return true;
        } else {
          const errorData = await resp.json();
          this.$refs.errorMessage.showMessage(errorData.message || "Ошибка на сервере");
          return false;
        }
      } catch (error) {
        this.$refs.errorMessage.showMessage("Ошибка на сервере");
        console.log(error);
        return false;
      }
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <h2>ВОЙТИ</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <input type="text" placeholder="Логин" id="login-input" v-model="login" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Пароль" id="password-input" v-model="password" required>
      </div>

      <input type="submit" value="Войти" class="btn-primary">
    </form>

    <button @click="$emit('switchToRegister')" class="btn-secondary">или зарегистрироваться</button>

    <ErrorMessage ref="errorMessage"/>
  </div>
</template>

<style scoped>
h2 {
  margin-bottom: 20px;
  font-size: 1.8em;
  font-weight: bold;
  color: #7f7fff;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 8px;
  font-size: 1em;
  color: #b0b0ff;
}


</style>