     1. Refaktor kodu - przeniesienie mapowan ponad controller
DONE 2. Uzycie GetMapping, PostMapping itp
DONE 3. Wypozyczenia/zwroty jako HQL a nie pętli
DONE 4. Aplikacja pozostałych uwag do kodu
DONE 5. Wykorzystanie pol z encji Adres z user w formularzach i widokach (np apartmentNumber itp)
DONE 6. Dodanie dodatkowych reguł walidacyjnych na apartmentNo, streetNo
7. Dodanie checkboxów z kursami (encja Course) 
    - modyfikacja formularza 
DONE    - stworzenie relacji z kursami jako @ManyToMany
    - checkbox z thymelaf
    - wybrać z widoku ID kursów, przekazać do kontrolera, wyszukać liste obiektów prezz CourseRepository i dopiąć do obiektu User na List<Course>
DONE    - widok do dodawania kursów
DONE 8. Odpowiednie katalogi w templates + refactor (strukturyzacja)
DONE 9. Tworzenie linków Thymeleaf
????    - edycja usera
DONE    - zwrot ksiazek
DONE 10. Zmiana pola author w ksiazce na obiekt Author + relacja + walidacje
DONE 11. Rozszerzenie modelu ksiazki o nowe pola:
DONE    - ISBN
DONE    - ilosc stron
DONE    - okladka twarda/miekka (boolean checbox)
DONE    - dorobienie walidacji, szczegolnie do ISBN
DONE 12. Dodanie annotacji Email do usera.
