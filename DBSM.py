import threading
import sqlite3

class ThreadSafeDB:
    def __init__(self, db_name):
        self.lock = threading.Lock() # Lock para obtener seguridad en operaciones concurrentes
        self.conn = sqlite3.connect(db_name) # Conexion a la base de datos recientemente creada
        self.create_tables()

    def create_tables(self): # Presentacion de la base de datos 
        with self.lock:
            cursor = self.conn.cursor()
            cursor.execute('''CREATE TABLE IF NOT EXISTS Robots (
                                id INTEGER PRIMARY KEY,
                                robot_type TEXT,
                                identification TEXT,
                                power_state TEXT,
                                location TEXT,
                                siren_count INTEGER
                            )''')
            cursor.execute('''CREATE TABLE IF NOT EXISTS LogEventos (
                                id INTEGER PRIMARY KEY,
                                event_type TEXT,
                                description TEXT,
                                timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
                            )''')
            cursor.execute('''CREATE TABLE IF NOT EXISTS EstadoPrograma (
                                id INTEGER PRIMARY KEY,
                                program_state TEXT
                            )''')
            cursor.execute('''CREATE TABLE IF NOT EXISTS VariablesEstaticas (
                                id INTEGER PRIMARY KEY,
                                variable_name TEXT,
                                value TEXT
                            )''')
            self.conn.commit()

    def add_robot(self, robot_data):
        with self.lock: #Seguridad
            cursor = self.conn.cursor()
            cursor.execute('''INSERT INTO Robots 
                              (robot_type, identification, power_state, location, siren_count) 
                              VALUES (?, ?, ?, ?, ?)''', robot_data)
            self.conn.commit()

    def list_robots(self):
        with self.lock:
            cursor = self.conn.cursor()
            cursor.execute('''SELECT * FROM Robots''')
            return cursor.fetchall()

    def update_robot(self, robot_id, new_robot_data):
        with self.lock:
            cursor = self.conn.cursor()
            cursor.execute('''UPDATE Robots 
                              SET robot_type=?, identification=?, power_state=?, location=?, siren_count=? 
                              WHERE id=?''', (*new_robot_data, robot_id))
            self.conn.commit()

    def delete_robot(self, robot_id):
        with self.lock:
            cursor = self.conn.cursor()
            cursor.execute('''DELETE FROM Robots WHERE id=?''', (robot_id,))
            self.conn.commit()

    def save_to_file(self, filename):
        with self.lock:
            with open(filename, 'w') as f:
                cursor = self.conn.cursor()
                cursor.execute('''SELECT * FROM Robots''')
                for record in cursor.fetchall():
                    f.write(','.join(map(str, record)) + '\n')

# Ejemplossssssss
if __name__ == "__main__":
    db = ThreadSafeDB('robots_database.db')
    db.add_robot(('Minero', 'DRN001', 'On', 'Warehouse A', 2))
    db.add_robot(('Extractor', 'RB002', 'Off', 'Factory B', 0))
    print(db.list_robots())
    db.update_robot(1, ('Minero', 'DRN001', 'Off', 'Warehouse B', 3))
    print(db.list_robots())
    db.delete_robot(1)
    print(db.list_robots())
    db.save_to_file('robots_data.csv')
