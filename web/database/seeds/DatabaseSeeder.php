<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        $this->command->info('Database Seeding Commence');
        $this->command->info('Seeding Users...');
        $this->call('UsersTableSeeder');
        $this->command->info('Seeding Tasks...');
        $this->call('TasksTableSeeder');
        $this->command->info('Finished! Seeder shutting down...');

    }
}
