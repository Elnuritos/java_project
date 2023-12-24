package ba.edu.ibu.demo.core.service;



import ba.edu.ibu.demo.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.demo.core.model.Publication;
import ba.edu.ibu.demo.core.model.User;
import ba.edu.ibu.demo.core.repository.PublicationRepository;
import ba.edu.ibu.demo.core.repository.UserRepository;
import ba.edu.ibu.demo.rest.dto.UserDTO;
import ba.edu.ibu.demo.rest.dto.UserRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    public UserService(UserRepository userRepository, PublicationRepository publicationRepository) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        // List<User> users = userRepository.findAllCustom();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }

    public UserDTO addUser(UserRequestDTO payload) {
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    public UserDTO updateUser(String id, UserRequestDTO payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public UserDTO filterByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmailLike(email);
        // Optional<User> user = userRepository.findByEmailCustom(email);
        return user.map(UserDTO::new).orElse(null);
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public List<Publication> getPublicationByUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        List<String> borrowedPublicationIds = user.get().getBorrowedPublications();
        List<Publication> publications = new ArrayList<>();
        for (String id: borrowedPublicationIds) {
            Optional<Publication> book = publicationRepository.findById(id);
            book.ifPresent(publications::add);
        }
        return publications;
    }
}