package lab.orion.web;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "guiceFilter", urlPatterns = {"/*"})
public class NetworkGuiceFilter extends GuiceFilter {
}
