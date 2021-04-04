module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      textColor: {
        "star-yellow": "#ffc107",
        "main-color": "#25cab9",
        "light-blue": "#13a8ca"
      },
      ringColor: {
        "main-color": "#25cab9"
      }
    },
    fontFamily: {
      "sans": ["Nunito", "sans-serif"]
    },
    backgroundColor: theme => ({
      ...theme("colors"),
      "facebook": "#3b5997",
      "flat-dark-gray": "#2F2F2F",
      "success": "#00AD5E",
      "dark-blue": "#051120",
      "dark-green": "#2C3134",
      "main-color": "#25cab9"
    }),
    borderColor: theme => ({
      ...theme("colors"),
      "main-color": "#25cab9",
    }),
  },
  variants: {
    extend: {
      backgroundColor: ["active"],
      opacity: ["active"],
    },

  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
