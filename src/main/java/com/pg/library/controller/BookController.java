package com.pg.library.controller;

import com.pg.library.model.Book;
import com.pg.library.rest.client.ExchangeRatesTable;
import com.pg.library.rest.client.Forecast.Forecast;
import com.pg.library.rest.client.Forecast.Weather;
import com.pg.library.rest.client.Rate;
import com.pg.library.service.BookService;
import com.pg.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value="/book")
public class BookController {

    private BookService bookService;
    private RestTemplate restTemplate;

    @Autowired
    public BookController(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    private Double getEuroRate() {
        URI uri = URI.create("http://api.nbp.pl/api/exchangerates/tables/a/");
        ResponseEntity<ExchangeRatesTable[]> response = restTemplate.getForEntity(uri, ExchangeRatesTable[].class);

        for (ExchangeRatesTable e : response.getBody()) {
            System.out.println(e);

            List<Rate> rates = e.getRates();

            for (Rate r : rates) {

                if (r.getCode().equals("EUR")) {
                    return r.getMid();
                }
            }

        }
        return null;
    }

    private Forecast getWarsawWeatherForecast() {
        URI uri2 = URI.create("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,PL&units=metric&APPID=d584923e4718b6dcaf40c1de837ffc2b");
        ResponseEntity<Forecast> response = restTemplate.getForEntity(uri2, Forecast.class);
        Forecast warsawWeatherForecast = response.getBody();

        return warsawWeatherForecast;
    }


    @GetMapping("/search/list")
    public String list(Model model) {
        model.addAttribute("list", bookService.list());
        return "/book/search/list";
    }

    @GetMapping("/add/form")
    public String form(Model model) {
        Double euroRate = getEuroRate();
        Forecast warsawWeatherForecast = getWarsawWeatherForecast();
        String city = warsawWeatherForecast.getName();
        Double latitude = warsawWeatherForecast.getCoord().getLat();
        Double longitude = warsawWeatherForecast.getCoord().getLon();
        List<Weather> weatherList = warsawWeatherForecast.getWeather();
        System.out.println(weatherList);
        Weather weather = weatherList.get(0);
        double temperature = warsawWeatherForecast.getMain().getTemp();
        double pressure = warsawWeatherForecast.getMain().getPressure();
        double humidity = warsawWeatherForecast.getMain().getHumidity();
        double windSpeed = warsawWeatherForecast.getWind().getSpeed();
        model.addAttribute("city", city);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        model.addAttribute("description", weather.getDescription());
        model.addAttribute("temperature", temperature);
        model.addAttribute("pressure", pressure);
        model.addAttribute("humidity", humidity);
        model.addAttribute("windSpeed", windSpeed);
        model.addAttribute("euroRate", euroRate);
        model.addAttribute("book", new Book());
        return "book/add/form";
    }

//    @GetMapping("/warsawWeather")
//    public String weather(Model model) {
//        Double euroRate = getEuroRate();
//        Forecast warsawWeatherForecast = getWarsawWeatherForecast();
//        String city = warsawWeatherForecast.getName();
//        Double latitude = warsawWeatherForecast.getCoord().getLat();
//        Double longitude = warsawWeatherForecast.getCoord().getLon();
//        List<Weather> weatherList = warsawWeatherForecast.getWeather();
//        System.out.println(w);
//        Weather weather = weatherList.get(0);
//        double temperature = warsawWeatherForecast.getMain().getTemp();
//        double pressure = warsawWeatherForecast.getMain().getPressure();
//        double humidity = warsawWeatherForecast.getMain().getHumidity();
//        double windSpeed = warsawWeatherForecast.getWind().getSpeed();
//        model.addAttribute("city", city);
//        model.addAttribute("latitude", latitude);
//        model.addAttribute("longitude", longitude);
//        model.addAttribute("description", weather.getDescription());
//        model.addAttribute("temperature", temperature);
//        model.addAttribute("pressure", pressure);
//        model.addAttribute("humidity", humidity);
//        model.addAttribute("windSpeed", windSpeed);
//        model.addAttribute("euroRate", euroRate);
//        return "/action/warsaw";
//    }


    @PostMapping("/add")
    public String save(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "/book/add/form";
        } else {
            bookService.add(book);
            return "redirect:/book/search/list";
        }
    }

    @GetMapping("/search/byword")
    public String search(@RequestParam("search") String word, Model model) {
        List<Book> listOfBooks = bookService.searchByWord(word);
        model.addAttribute("list", listOfBooks);
        return "/book/search/list";
    }

    @GetMapping("/search/borrowed")
    public String borrowedList(Model model) {
        model.addAttribute("list", bookService.borrowedList());
        return "/book/search/borrowed";
    }

    @GetMapping("/search/borrowed/byword")
    public String searchBorrowedByTitle(@RequestParam("search") String word, Model model) {
        model.addAttribute("list", bookService.searchBorrowedByWord(word));
        return "/book/search/borrowed";
    }

    @GetMapping("/search/available")
    public String availableBooksList(Model model) {
        model.addAttribute("list", bookService.availableList());
        return "/book/search/available";
    }

    @GetMapping("/search/available/{userId}")
    public String availableBooks(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("list", bookService.availableList());
        model.addAttribute("userId", userId);
        return "/book/search/available";
    }

    @GetMapping("/search/available/byword")
    public String searchAvaileableByTitle(@RequestParam("search") String word, Model model) {
        model.addAttribute("list", bookService.searchAvailableByWord(word));
        return "/book/search/available";
    }

    @GetMapping("/return/{bookId}")
    public String returnBook(@PathVariable("bookId") Integer bookId) {

        Book book = bookService.searchById(bookId);
        book.setUser(null);
        bookService.update(book);
        return "/action/return";
    }

}
