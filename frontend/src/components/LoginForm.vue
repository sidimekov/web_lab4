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
    handleLogin() {
      const resp = fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        body: JSON.stringify({login: this.login, password: this.password}),
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
      });

      resp.then(response => {
        const token = response.data.token;
        localStorage.setItem("token", token);
        return true;
      });
      resp.catch(error => {
        this.$refs.errorMessage.showMessage("эх ошибка на сервере");
        console.log(error);
      });
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