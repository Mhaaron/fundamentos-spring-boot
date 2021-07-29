package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//Este esteriotipo o notación se usa para poder inyectar esta interface como dependencia
@Repository
//Cambiamos la extensión de JpaRepository ya que no se utilizará más
//PaginAndSortin es una interfaz que tiene todos los métodos que tienen que ver con el pageable
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    //Reliza Querys con JPQL a nivel de objetos no de tablas
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Query methods es una alternativa a JPQL
    List<User> findByName(String name);

    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByIdAsc(String name);
    //Usar Contain en lugar de like
    List<User> findByNameContainingOrderByIdDesc(String name);

    List<User> findByNameOrEmailContainingOrderByNameAsc(String name, String email);

    //Query a nivel de Jpql con named parameter, se representan los datos a nivel de clase no a nivel de entida
    // ya que estamos inicializando los datos en la Query
    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            " FROM User u" +
            " WHERE u.birthDate=:parametroFecha " +
            " AND u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

    //Creamos nuestro propio método findAll en nuestro repository apartir del de la interfaz PaginAnd...
    List<User> findAll();
}
