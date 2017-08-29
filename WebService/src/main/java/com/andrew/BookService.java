package com.andrew;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BookReader bookReader;

    /**
     * �ϥέ쥻�� RestTemplate �ާ@
     *
     * @param book
     * @return Book
     */
    public Book createByRestTemplate(Book book) {
        ParameterizedTypeReference<Book> ptr =
                new ParameterizedTypeReference<Book>() {
                };
        HttpEntity<Book> request = new HttpEntity<>(book);
        ResponseEntity<Book> responseEntity =
                new RestTemplate().exchange("http://localhost:8000/book",
                        HttpMethod.POST, request, ptr);
        return responseEntity.getBody();
    }

    /**
     * �ϥέ쥻�� RestTemplate �ާ@
     *
     * @param bookid
     * @return Resource<Book>
     */
    public Resource<Book> getByRestTemplate(Integer bookid) {
        ParameterizedTypeReference<Resource<Book>> ptr =
                new ParameterizedTypeReference<Resource<Book>>() {
                };
        ResponseEntity<Resource<Book>> responseEntity =
                new RestTemplate().exchange("http://localhost:8000/book/" + bookid,
                        HttpMethod.GET, null, ptr);
        return responseEntity.getBody();
    }

    /**
     * �ϥΦ۰ʭt�����ž���
     *
     * @param book
     * @return ResponseEntity<Resources<Book>>
     */
    public Book createByLoadBalancedRestTemplate(Book book) {
        ParameterizedTypeReference<Book> ptr =
                new ParameterizedTypeReference<Book>() {
                };
        HttpEntity<Book> request = new HttpEntity<>(book);
        ResponseEntity<Book> responseEntity =
                this.restTemplate.exchange("http://book-service/book",
                        HttpMethod.POST, request, ptr);
        return responseEntity.getBody();
    }

    /**
     * �ϥΦ۰ʭt�����ž���
     *
     * @param bookid
     * @return Resource<Book>
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Resource<Book> getByLoadBalancedRestTemplate(Integer bookid) {
        ParameterizedTypeReference<Resource<Book>> ptr =
                new ParameterizedTypeReference<Resource<Book>>() {
                };
        ResponseEntity<Resource<Book>> responseEntity =
                this.restTemplate.exchange("http://book-service/book/" + bookid,
                        HttpMethod.GET, null, ptr);
        return responseEntity.getBody();
    }

    /**
     * �ϥ��n���� FeignClient
     *
     * @param book
     * @return Resources<Book>
     */
    public Book createByFeignClient(Book book) {
        return bookReader.create(book);
    }

    /**
     * �ϥ��n���� FeignClient
     *
     * @param bookid
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Resource<Book> getByFeignClient(Integer bookid) {
        return bookReader.get(bookid);
    }

    public Resource<Book> fallback(Integer bookid) {
        return null;
    }


}
