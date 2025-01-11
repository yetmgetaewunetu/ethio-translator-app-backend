const slider = document.getElementById("edu-slider");
const eduFigures = document.querySelectorAll(".edu-slide");
const eduPrev = document.querySelector("#edu-prev");
const eduNext = document.getElementById("edu-next");

// responsive menu
const navbar = document.querySelector("aside");
const toggleNavbar = () => {
  navbar.classList.toggle("show-nav");
};

var edu_slider = 0;

eduPrev.addEventListener("click", () => {
  edu_slider = (edu_slider - 1) % eduFigures.length;

  slider.style.transform = `translateX(${edu_slider * 100}%)`;
});

eduNext.addEventListener("click", () => {
  edu_slider = (edu_slider + 1) % eduFigures.length;

  slider.style.transform = `translateX(-${edu_slider * 100}%)`;
});

// eduPrev.addEventListener("click", prevSlide);
// eduNext.addEventListener("click", nextSlide);
