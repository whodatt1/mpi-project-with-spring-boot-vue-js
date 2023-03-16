package com.example.mpi.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.MovieDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
//	@GetMapping("/upcoming/{adult}")
	@GetMapping("/main/upcoming/{adult}")
	public ResponseEntity<List<MovieDto>> getMovieForMainUpComing(@PathVariable("adult") String adult) {
		
		try {
			List<MovieDto> movieForMainUpComing = movieService.getMovieForMainUpComing(adult);
			
			if (movieForMainUpComing.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieForMainUpComing, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/main/latest/{adult}")
	public ResponseEntity<List<MovieDto>> getMovieForMainLatest(@PathVariable("adult") String adult) {
		try {
			List<MovieDto> movieForMainLatest = movieService.getMovieForMainLatest(adult);
			
			if (movieForMainLatest.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieForMainLatest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/main/top/{adult}")
	public ResponseEntity<List<MovieDto>> getMovieForMainTop(@PathVariable("adult") String adult) {
		try {
			List<MovieDto> movieForMainTop = movieService.getMovieForMainTop(adult);
			
			if (movieForMainTop.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieForMainTop, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/main/popular/{adult}")
	public ResponseEntity<List<MovieDto>> getMovieForMainPopular(@PathVariable("adult") String adult) {
		try {
			List<MovieDto> movieForMainPopuplar = movieService.getMovieForMainPopular(adult);
			
			if (movieForMainPopuplar.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieForMainPopuplar, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<MovieDto> getMovieDetail(@PathVariable("id") String id) {
		try {
			MovieDto movieDetail = movieService.getMovieDetail(id);
			
			if (movieDetail == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieDetail, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/list/{pageNo}/{adult}")
	public ResponseEntity<PagingHandler> getMovieListAll(@PathVariable("pageNo") int pageNo, @PathVariable("adult") String adult) {
		
		try {
			Criteria criteria = new Criteria(pageNo, 20);
			criteria.setAdult(adult);
			PagingHandler movieListAll = movieService.getMovieListAll(criteria);
			if (movieListAll.getMovieList().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieListAll, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list/{pageNo}/{genre}/{sortBy}/{adult}")
	public ResponseEntity<PagingHandler> getMovieListAllForResult(@PathVariable("pageNo") int pageNo, @PathVariable(name = "genre", required = false) String genre, @PathVariable(name = "sortBy", required = false) String sortBy, @PathVariable("adult") String adult) {
		
		try {
			Criteria criteria = new Criteria(pageNo, 20);
			criteria.setGenre(genre);
			criteria.setSortBy(sortBy);
			criteria.setAdult(adult);
			
			PagingHandler movieListAll = movieService.getMovieListAll(criteria);
			if (movieListAll.getMovieList().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieListAll, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list/{pageNo}/{title}/{adult}")
	public ResponseEntity<PagingHandler> getMovieListAllForResultTitle(@PathVariable("pageNo") int pageNo, @PathVariable(name = "title", required = false) String title, @PathVariable("adult") String adult) {
		
		try {
			Criteria criteria = new Criteria(pageNo, 20);
			criteria.setTitle(title);
			criteria.setAdult(adult);
			
			PagingHandler movieListAll = movieService.getMovieListAll(criteria);
			if (movieListAll.getMovieList().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(movieListAll, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
