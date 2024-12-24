<script>
export default {
  name: "LoginForm",

  data() {
    return {
      login: "",
      password: ""
    }
  },

  methods: {
    handleLogin() {
      // login
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <h2>ВОЙТИ</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <input type="text" placeholder="Логин" id="login-input" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Пароль" id="password-input" required>
      </div>

      <input type="submit" value="Войти" class="btn-primary">
    </form>

    <button @click="$emit('switchToRegister')" class="btn-secondary">или зарегистрироваться</button>
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