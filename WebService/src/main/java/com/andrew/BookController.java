package com.andrew;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "api")
@Api(tags = "Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "�s�W�ѥ�", notes = "�ϥ� IP �ӵo�e�ШD", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "�s�ɦ��\")})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "v1/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createByRestTemplate(
            @ApiParam(required = true, value = "�ѥ����e") @RequestBody Book book) {
        return bookService.createByRestTemplate(book);
    }

    @ApiOperation(value = "�s�W�ѥ�", notes = "�ϥ� @LoadBalanced RestTemplate �ӵo�e�ШD", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "�s�ɦ��\")})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "v2/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createByLoadBalancedRestTemplate(
            @ApiParam(required = true, value = "�ѥ����e") @RequestBody Book book) {
        return bookService.createByLoadBalancedRestTemplate(book);
    }

    @ApiOperation(value = "�s�W�ѥ�", notes = "�ϥ� FeignClient �ӵo�e�ШD", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "�s�ɦ��\")})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "v3/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createByFeignClient(
            @ApiParam(required = true, value = "�ѥ����e") @RequestBody Book book) {
        return bookService.createByFeignClient(book);
    }

    @ApiOperation(value = "���o�ѥ����e", notes = "�ϥ� IP �ӵo�e�ШD", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ѥ���T")})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/v1/book/{bookid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Book> getByRestTemplate(
            @ApiParam(required = true, name = "bookid", value = "�ѥ�ID") @PathVariable Integer bookid) {
        return bookService.getByRestTemplate(bookid);
    }


    @ApiOperation(value = "���o�ѥ����e", notes = "�ϥ� @LoadBalanced RestTemplate �ӵo�e�ШD", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ѥ���T")})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/v2/book/{bookid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Book> getByLoadBalancedRestTemplate(
            @ApiParam(required = true, name = "bookid", value = "�ѥ�ID") @PathVariable Integer bookid) {
        Resource<Book> bookResource = bookService.getByLoadBalancedRestTemplate(bookid);
        if (bookResource == null) {
            throw new ServiceUnavailableException();
        }
        return bookResource;
    }


    @ApiOperation(value = "���o�ѥ����e", notes = "�ϥ� FeignClient �ӵo�e�ШD", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ѥ���T")})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/v3/book/{bookid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Book> getByFeignClient(
            @ApiParam(required = true, name = "bookid", value = "�ѥ�ID") @PathVariable Integer bookid) {
        Resource<Book> bookResource = bookService.getByFeignClient(bookid);
        if (bookResource == null) {
            throw new ServiceUnavailableException();
        }
        return bookResource;
    }
}